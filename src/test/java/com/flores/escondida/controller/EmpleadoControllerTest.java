package com.flores.escondida.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flores.escondida.dto.EmpleadoDTO;
import com.flores.escondida.entity.Empleado;
import com.flores.escondida.service.EmpleadoService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 * Plantilla de prueba para EmpleadoController.
 * Esta clase utiliza @WebMvcTest para probar solo la capa web,
 * mockeando las dependencias de servicio y mapeo.
 */
@WebMvcTest(EmpleadoController.class)
class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmpleadoService empleadoService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    @WithMockUser
    void getAll_ShouldReturnStatusOk() throws Exception {
        // Arrange
        when(empleadoService.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/v1/empleados")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void create_ShouldReturnCreatedEmpleado() throws Exception {
        // 1. Arrange (Preparar los datos)
        EmpleadoDTO inputDto = new EmpleadoDTO();
        inputDto.setNombre("Juan");
        inputDto.setApellido("Perez");
        inputDto.setEmail("juan@gmail.com");
        inputDto.setTelefono("999999999");
        inputDto.setCiudad("Lima");

        Empleado entity = new Empleado();
        entity.setId(1L);
        entity.setNombre("Juan");
        entity.setApellido("Perez");

        // Mockear el comportamiento del mapper y del servicio
        when(modelMapper.map(any(EmpleadoDTO.class), any())).thenReturn(entity);
        when(empleadoService.save(any(Empleado.class))).thenReturn(entity);
        when(modelMapper.map(any(Empleado.class), any())).thenReturn(inputDto);

        // 2. Act (Ejecutar la acción)
        mockMvc.perform(post("/api/v1/empleados")
                .with(csrf()) // Agregamos el token CSRF para evitar el error 403
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto))) // Convertir objeto a JSON

                // 3. Assert (Verificar el resultado)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"));
    }
}

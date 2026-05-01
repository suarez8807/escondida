package com.flores.escondida.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flores.escondida.dto.ContratoDTO;
import com.flores.escondida.entity.Contrato;
import com.flores.escondida.security.JwtAuthenticationFilter;
import com.flores.escondida.security.JwtService;
import com.flores.escondida.service.ContratoService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContratoController.class)
@AutoConfigureMockMvc(addFilters = false)
class ContratoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContratoService contratoService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    @WithMockUser
    void getAll_ShouldReturnList() throws Exception {
        when(contratoService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/contratos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void create_ShouldReturnCreatedContrato() throws Exception {
        ContratoDTO dto = new ContratoDTO();
        dto.setEmpleadoId(1L);
        dto.setFechaInicio("2024-01-01");
        dto.setTipocontrato("Mensual");
        dto.setSalario(2000);
        dto.setFechainicioContrato(LocalDate.of(2024, 1, 1));

        Contrato entity = new Contrato();
        entity.setId(1L);

        when(modelMapper.map(any(ContratoDTO.class), eq(Contrato.class))).thenReturn(entity);
        when(contratoService.save(any(Contrato.class))).thenReturn(entity);
        when(modelMapper.map(any(Contrato.class), eq(ContratoDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/contratos")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empleadoId").value(1L));
    }
}

package com.flores.escondida.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flores.escondida.dto.SalarioDTO;
import com.flores.escondida.entity.Salario;
import com.flores.escondida.service.SalarioService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalarioController.class)
class SalarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SalarioService salarioService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    @WithMockUser
    void getAll_ShouldReturnList() throws Exception {
        when(salarioService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/salarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void create_ShouldReturnCreatedSalario() throws Exception {
        SalarioDTO dto = new SalarioDTO();
        dto.setContratoId(1L);
        dto.setSalarioBase(new BigDecimal("2500.00"));
        dto.setAuxTransporte(new BigDecimal("140.00"));

        Salario entity = new Salario();
        entity.setId(1L);

        when(modelMapper.map(any(SalarioDTO.class), eq(Salario.class))).thenReturn(entity);
        when(salarioService.save(any(Salario.class))).thenReturn(entity);
        when(modelMapper.map(any(Salario.class), eq(SalarioDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/salarios")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contratoId").value(1L));
    }
}

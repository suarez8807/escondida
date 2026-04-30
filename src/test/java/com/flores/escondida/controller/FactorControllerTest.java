package com.flores.escondida.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flores.escondida.dto.FactorDTO;
import com.flores.escondida.entity.Factor;
import com.flores.escondida.service.FactorService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FactorController.class)
class FactorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FactorService factorService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    @WithMockUser
    void getAll_ShouldReturnList() throws Exception {
        when(factorService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/factores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void create_ShouldReturnCreatedFactor() throws Exception {
        FactorDTO dto = new FactorDTO();
        dto.setVersion("V1");
        dto.setFactorHoraNormal(1.0);
        dto.setFactorHoraExtra(1.5);
        dto.setFactorHoraDominical(2.0);
        dto.setFactorHoraNocturna(1.35);
        dto.setFactorHoraExtraNocturna(1.85);
        dto.setFactorHoraExtraDominical(2.5);
        dto.setFactorHoraExtraNocturnaDominical(3.0);

        Factor entity = new Factor();
        entity.setId(1L);

        when(modelMapper.map(any(FactorDTO.class), eq(Factor.class))).thenReturn(entity);
        when(factorService.save(any(Factor.class))).thenReturn(entity);
        when(modelMapper.map(any(Factor.class), eq(FactorDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/factores")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("V1"));
    }
}

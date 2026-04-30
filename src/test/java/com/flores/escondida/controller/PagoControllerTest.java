package com.flores.escondida.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flores.escondida.dto.PagoDTO;
import com.flores.escondida.entity.Pago;
import com.flores.escondida.service.PagoService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
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

@WebMvcTest(PagoController.class)
class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PagoService pagoService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    @WithMockUser
    void getAll_ShouldReturnList() throws Exception {
        when(pagoService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/pagos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void create_ShouldReturnCreatedPago() throws Exception {
        PagoDTO dto = new PagoDTO();
        dto.setContratoId(1L);
        dto.setSalarioId(1L);
        dto.setFechaPago(LocalDate.now());
        dto.setValorPago(new BigDecimal("1500.00"));

        Pago entity = new Pago();
        entity.setId(1L);

        when(modelMapper.map(any(PagoDTO.class), eq(Pago.class))).thenReturn(entity);
        when(pagoService.save(any(Pago.class))).thenReturn(entity);
        when(modelMapper.map(any(Pago.class), eq(PagoDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/pagos")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contratoId").value(1L));
    }
}

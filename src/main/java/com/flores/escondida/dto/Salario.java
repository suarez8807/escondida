package com.flores.escondida.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Salario {
    private long id;

    @DecimalMin(value = "0.0", inclusive = false, message = "El salario base debe ser mayor a 0")
    private BigDecimal salarioBase;

    @DecimalMin(value = "0.0", inclusive = false, message = "El auxilio de transporte debe ser mayor a 0")
    private BigDecimal auxTransporte;
    private String fechaCreacion;
    private String fechaActualizacion;
    private int estado;
}

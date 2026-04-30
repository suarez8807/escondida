package com.flores.escondida.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalarioDTO {
    private Long id;

    @NotNull(message = "El ID del contrato es obligatorio")
    private Long contratoId;

    @DecimalMin(value = "0.0", inclusive = false, message = "El salario base debe ser mayor a 0")
    private BigDecimal salarioBase;

    @DecimalMin(value = "0.0", inclusive = false, message = "El auxilio de transporte debe ser mayor a 0")
    private BigDecimal auxTransporte;

    private String fechaCreacion;
    private String fechaActualizacion;
    private int estado;
}

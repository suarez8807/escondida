package com.flores.escondida.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagoDTO {
    private Long id;

    @NotNull(message = "El ID del salario es obligatorio")
    private Long salarioId;

    @NotNull(message = "El ID del contrato es obligatorio")
    private Long contratoId;

    private int estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDate fechaPago;

    @DecimalMin(value = "0.0", inclusive = false, message = "El valor del pago debe ser mayor a 0")
    private BigDecimal valorPago;
    
    private BigDecimal bonificacion;
    private BigDecimal deduccion;
    private String metodoPago;
    private String referenciaPago;
    private String estadoPago;
    private String observacion;
}

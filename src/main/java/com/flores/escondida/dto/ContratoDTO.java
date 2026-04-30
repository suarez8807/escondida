package com.flores.escondida.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratoDTO {

    private Long id;

    @NotNull(message = "El ID del empleado es obligatorio")
    private Long empleadoId;

    @NotBlank(message = "La fecha de inicio es obligatoria")
    private String fechaInicio;

    private String fechaFin;

    @Min(value = 1, message = "El salario debe ser mayor a 0")
    private int salario;

    private int tipopago;

    @NotBlank(message = "El tipo de contrato es obligatorio")
    private String tipocontrato;

    private int estado;

    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

    @NotNull(message = "La fecha de inicio de contrato es obligatoria")
    private LocalDate fechainicioContrato;

    private LocalDate fechafinContrato;
}

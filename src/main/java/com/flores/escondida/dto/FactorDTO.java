package com.flores.escondida.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FactorDTO {
    private Long id;

    @NotBlank(message = "La version es obligatoria")
    private String version;

    @Positive(message = "El factor hora normal debe ser mayor a 0")
    private double factorHoraNormal;

    @Positive(message = "El factor hora extra debe ser mayor a 0")
    private double factorHoraExtra;

    @Positive(message = "El factor hora dominical debe ser mayor a 0")
    private double factorHoraDominical;

    @Positive(message = "El factor hora nocturna debe ser mayor a 0")
    private double factorHoraNocturna;

    @Positive(message = "El factor hora extra nocturna debe ser mayor a 0")
    private double factorHoraExtraNocturna;

    @Positive(message = "El factor hora extra dominical debe ser mayor a 0")
    private double factorHoraExtraDominical;

    @Positive(message = "El factor hora extra nocturna dominical debe ser mayor a 0")
    private double factorHoraExtraNocturnaDominical;
    private int estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
}

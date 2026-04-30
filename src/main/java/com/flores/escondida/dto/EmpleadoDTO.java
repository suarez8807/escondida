package com.flores.escondida.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @NotBlank(message = "La direccion es obligatoria")
    private String ciudad;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private LocalDate fechaNacimiento;
    private String genero;
    private String tipoDocumento;
    private String numeroDocumento;
    private LocalDate fechaInicioContrato;
    private LocalDate fechaFinContrato;
    private int estado;
}

package com.flores.escondida.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empleados")
@Getter
@Setter
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;
    
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;
    
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    
    private String genero;
    
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    
    @Column(name = "numero_documento")
    private String numeroDocumento;
    
    @Column(name = "fecha_inicio_contrato")
    private LocalDate fechaInicioContrato;
    
    @Column(name = "fecha_fin_contrato")
    private LocalDate fechaFinContrato;
    
    private int estado;
}

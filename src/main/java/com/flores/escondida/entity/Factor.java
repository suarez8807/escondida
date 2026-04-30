package com.flores.escondida.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "factores")
@Getter
@Setter
public class Factor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String version;
    
    @Column(name = "factor_hora_normal")
    private double factorHoraNormal;
    
    @Column(name = "factor_hora_extra")
    private double factorHoraExtra;
    
    @Column(name = "factor_hora_dominical")
    private double factorHoraDominical;
    
    @Column(name = "factor_hora_nocturna")
    private double factorHoraNocturna;
    
    @Column(name = "factor_hora_extra_nocturna")
    private double factorHoraExtraNocturna;
    
    @Column(name = "factor_hora_extra_dominical")
    private double factorHoraExtraDominical;
    
    @Column(name = "factor_hora_extra_nocturna_dominical")
    private double factorHoraExtraNocturnaDominical;
    
    private int estado;
    
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;
}

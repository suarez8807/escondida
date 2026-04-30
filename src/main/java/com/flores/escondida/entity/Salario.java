package com.flores.escondida.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "salarios")
@Getter
@Setter
public class Salario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "salario_base")
    private BigDecimal salarioBase;
    
    @Column(name = "aux_transporte")
    private BigDecimal auxTransporte;
    
    @Column(name = "fecha_creacion")
    private String fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    private int estado;
}

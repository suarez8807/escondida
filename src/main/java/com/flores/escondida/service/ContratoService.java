package com.flores.escondida.service;

import com.flores.escondida.entity.Contrato;
import java.util.List;
import java.util.Optional;

public interface ContratoService {
    List<Contrato> findAll();
    Optional<Contrato> findById(Long id);
    Contrato save(Contrato contrato);
    void deleteById(Long id);
    List<Contrato> findByEmpleadoId(Long empleadoId);
}

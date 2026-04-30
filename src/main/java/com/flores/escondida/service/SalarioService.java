package com.flores.escondida.service;

import com.flores.escondida.entity.Salario;
import java.util.List;
import java.util.Optional;

public interface SalarioService {
    List<Salario> findAll();
    Optional<Salario> findById(Long id);
    Salario save(Salario salario);
    void deleteById(Long id);
    List<Salario> findByContratoId(Long contratoId);
}

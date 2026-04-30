package com.flores.escondida.service;

import com.flores.escondida.entity.Empleado;
import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAll();
    Optional<Empleado> findById(Long id);
    Empleado save(Empleado empleado);
    void deleteById(Long id);
    Optional<Empleado> findByNumeroDocumento(String numeroDocumento);
}

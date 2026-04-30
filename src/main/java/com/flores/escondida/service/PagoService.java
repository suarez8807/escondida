package com.flores.escondida.service;

import com.flores.escondida.entity.Pago;
import java.util.List;
import java.util.Optional;

public interface PagoService {
    List<Pago> findAll();
    Optional<Pago> findById(Long id);
    Pago save(Pago pago);
    void deleteById(Long id);
    List<Pago> findByContratoId(Long contratoId);
}

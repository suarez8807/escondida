package com.flores.escondida.service;

import com.flores.escondida.entity.Factor;
import java.util.List;
import java.util.Optional;

public interface FactorService {
    List<Factor> findAll();
    Optional<Factor> findById(Long id);
    Factor save(Factor factor);
    void deleteById(Long id);
    Optional<Factor> findActiveFactor();
}

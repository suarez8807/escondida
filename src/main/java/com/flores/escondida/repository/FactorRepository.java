package com.flores.escondida.repository;

import com.flores.escondida.entity.Factor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactorRepository extends JpaRepository<Factor, Long> {
    Optional<Factor> findByVersion(String version);
    Optional<Factor> findByEstado(int estado);
}

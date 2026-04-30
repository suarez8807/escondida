package com.flores.escondida.repository;

import com.flores.escondida.entity.Salario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalarioRepository extends JpaRepository<Salario, Long> {
    List<Salario> findByContratoId(Long contratoId);
}

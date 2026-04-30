package com.flores.escondida.repository;

import com.flores.escondida.entity.Contrato;
import com.flores.escondida.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByEmpleadoId(Long empleadoId);

    List<Contrato> findByEstado(int estado);

    boolean existsByEmpleadoIdAndEstado(Long empleadoId, int estado);
}

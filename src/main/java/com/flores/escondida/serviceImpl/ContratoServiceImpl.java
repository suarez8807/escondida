package com.flores.escondida.serviceImpl;

import com.flores.escondida.entity.Contrato;
import com.flores.escondida.repository.ContratoRepository;
import com.flores.escondida.service.ContratoService;
import com.flores.escondida.exception.BadRequestException;
import com.flores.escondida.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public List<Contrato> findAll() {
        return contratoRepository.findAll();
    }

    @Override
    public Optional<Contrato> findById(Long id) {
        return Optional.ofNullable(contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con id: " + id)));
    }

    @Override
    public Contrato save(Contrato contrato) {
        if (contratoRepository.existsByEmpleadoIdAndEstado(contrato.getEmpleado().getId(), 1)) {
            throw new BadRequestException("El empleado ya tiene un contrato activo");
        }
        return contratoRepository.save(contrato);
    }

    @Override
    public void deleteById(Long id) {
        if (!contratoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Contrato no encontrado con id: " + id);
        }
        contratoRepository.deleteById(id);
    }

    @Override
    public List<Contrato> findByEmpleadoId(Long empleadoId) {
        List<Contrato> contratos = contratoRepository.findByEmpleadoId(empleadoId);
        if (contratos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron contratos para el empleado con id: " + empleadoId);
        }
        return contratos;
    }
}

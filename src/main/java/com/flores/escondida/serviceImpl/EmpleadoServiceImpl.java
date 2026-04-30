package com.flores.escondida.serviceImpl;

import com.flores.escondida.entity.Empleado;
import com.flores.escondida.repository.EmpleadoRepository;
import com.flores.escondida.service.EmpleadoService;
import com.flores.escondida.exception.BadRequestException;
import com.flores.escondida.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        return Optional.ofNullable(empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id)));
    }

    @Override
    public Empleado save(Empleado empleado) {
        // Validar si ya existe un empleado con el mismo número de documento
        if (empleado.getId() == null && empleadoRepository.findByNumeroDocumento(empleado.getNumeroDocumento()).isPresent()) {
            throw new BadRequestException("Ya existe un empleado con el número de documento: " + empleado.getNumeroDocumento());
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public void deleteById(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Empleado no encontrado con id: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    @Override
    public Optional<Empleado> findByNumeroDocumento(String numeroDocumento) {
        return Optional.ofNullable(empleadoRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con documento: " + numeroDocumento)));
    }
}

package com.flores.escondida.serviceImpl;

import com.flores.escondida.entity.Salario;
import com.flores.escondida.repository.SalarioRepository;
import com.flores.escondida.service.SalarioService;
import com.flores.escondida.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalarioServiceImpl implements SalarioService {

    @Autowired
    private SalarioRepository salarioRepository;

    @Override
    public List<Salario> findAll() {
        return salarioRepository.findAll();
    }

    @Override
    public Optional<Salario> findById(Long id) {
        return Optional.ofNullable(salarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salario no encontrado con id: " + id)));
    }

    @Override
    public Salario save(Salario salario) {
        return salarioRepository.save(salario);
    }

    @Override
    public void deleteById(Long id) {
        if (!salarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Salario no encontrado con id: " + id);
        }
        salarioRepository.deleteById(id);
    }

    @Override
    public List<Salario> findByContratoId(Long contratoId) {
        List<Salario> salarios = salarioRepository.findByContratoId(contratoId);
        if (salarios.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron salarios para el contrato con id: " + contratoId);
        }
        return salarios;
    }
}

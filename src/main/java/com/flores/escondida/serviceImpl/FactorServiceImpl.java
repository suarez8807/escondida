package com.flores.escondida.serviceImpl;

import com.flores.escondida.entity.Factor;
import com.flores.escondida.repository.FactorRepository;
import com.flores.escondida.service.FactorService;
import com.flores.escondida.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactorServiceImpl implements FactorService {

    @Autowired
    private FactorRepository factorRepository;

    @Override
    public List<Factor> findAll() {
        return factorRepository.findAll();
    }

    @Override
    public Optional<Factor> findById(Long id) {
        return Optional.ofNullable(factorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factor no encontrado con id: " + id)));
    }

    @Override
    public Factor save(Factor factor) {
        return factorRepository.save(factor);
    }

    @Override
    public void deleteById(Long id) {
        if (!factorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Factor no encontrado con id: " + id);
        }
        factorRepository.deleteById(id);
    }

    @Override
    public Optional<Factor> findActiveFactor() {
        return Optional.ofNullable(factorRepository.findByEstado(1)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un factor activo")));
    }
}

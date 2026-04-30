package com.flores.escondida.serviceImpl;

import com.flores.escondida.entity.Pago;
import com.flores.escondida.repository.PagoRepository;
import com.flores.escondida.service.PagoService;
import com.flores.escondida.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> findById(Long id) {
        return Optional.ofNullable(pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + id)));
    }

    @Override
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void deleteById(Long id) {
        if (!pagoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Pago no encontrado con id: " + id);
        }
        pagoRepository.deleteById(id);
    }

    @Override
    public List<Pago> findByContratoId(Long contratoId) {
        List<Pago> pagos = pagoRepository.findByContratoId(contratoId);
        if (pagos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pagos para el contrato con id: " + contratoId);
        }
        return pagos;
    }
}

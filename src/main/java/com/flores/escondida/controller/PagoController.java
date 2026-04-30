package com.flores.escondida.controller;

import com.flores.escondida.dto.PagoDTO;
import com.flores.escondida.entity.Pago;
import com.flores.escondida.service.PagoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PagoDTO> getAll() {
        return pagoService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> getById(@PathVariable Long id) {
        return pagoService.findById(id)
                .map(pago -> ResponseEntity.ok(convertToDTO(pago)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PagoDTO> create(@Valid @RequestBody PagoDTO pagoDTO) {
        Pago pago = convertToEntity(pagoDTO);
        Pago savedPago = pagoService.save(pago);
        return ResponseEntity.ok(convertToDTO(savedPago));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/contrato/{contratoId}")
    public List<PagoDTO> getByContrato(@PathVariable Long contratoId) {
        return pagoService.findByContratoId(contratoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PagoDTO convertToDTO(Pago pago) {
        return modelMapper.map(pago, PagoDTO.class);
    }

    private Pago convertToEntity(PagoDTO pagoDTO) {
        return modelMapper.map(pagoDTO, Pago.class);
    }
}

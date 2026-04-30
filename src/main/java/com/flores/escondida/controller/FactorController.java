package com.flores.escondida.controller;

import com.flores.escondida.dto.FactorDTO;
import com.flores.escondida.entity.Factor;
import com.flores.escondida.service.FactorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/factores")
@CrossOrigin(origins = "*")
public class FactorController {

    @Autowired
    private FactorService factorService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<FactorDTO> getAll() {
        return factorService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactorDTO> getById(@PathVariable Long id) {
        return factorService.findById(id)
                .map(factor -> ResponseEntity.ok(convertToDTO(factor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FactorDTO> create(@Valid @RequestBody FactorDTO factorDTO) {
        Factor factor = convertToEntity(factorDTO);
        Factor savedFactor = factorService.save(factor);
        return ResponseEntity.ok(convertToDTO(savedFactor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        factorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activo")
    public ResponseEntity<FactorDTO> getActive() {
        return factorService.findActiveFactor()
                .map(factor -> ResponseEntity.ok(convertToDTO(factor)))
                .orElse(ResponseEntity.notFound().build());
    }

    private FactorDTO convertToDTO(Factor factor) {
        return modelMapper.map(factor, FactorDTO.class);
    }

    private Factor convertToEntity(FactorDTO factorDTO) {
        return modelMapper.map(factorDTO, Factor.class);
    }
}

package com.flores.escondida.controller;

import com.flores.escondida.dto.SalarioDTO;
import com.flores.escondida.entity.Salario;
import com.flores.escondida.service.SalarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/salarios")
@CrossOrigin(origins = "*")
public class SalarioController {

    @Autowired
    private SalarioService salarioService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<SalarioDTO> getAll() {
        return salarioService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalarioDTO> getById(@PathVariable Long id) {
        return salarioService.findById(id)
                .map(salario -> ResponseEntity.ok(convertToDTO(salario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SalarioDTO> create(@Valid @RequestBody SalarioDTO salarioDTO) {
        Salario salario = convertToEntity(salarioDTO);
        Salario savedSalario = salarioService.save(salario);
        return ResponseEntity.ok(convertToDTO(savedSalario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        salarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/contrato/{contratoId}")
    public List<SalarioDTO> getByContrato(@PathVariable Long contratoId) {
        return salarioService.findByContratoId(contratoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SalarioDTO convertToDTO(Salario salario) {
        return modelMapper.map(salario, SalarioDTO.class);
    }

    private Salario convertToEntity(SalarioDTO salarioDTO) {
        return modelMapper.map(salarioDTO, Salario.class);
    }
}

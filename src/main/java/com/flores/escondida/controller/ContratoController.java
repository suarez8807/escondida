package com.flores.escondida.controller;

import com.flores.escondida.dto.ContratoDTO;
import com.flores.escondida.entity.Contrato;
import com.flores.escondida.service.ContratoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/contratos")
@CrossOrigin(origins = "*")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ContratoDTO> getAll() {
        return contratoService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> getById(@PathVariable Long id) {
        return contratoService.findById(id)
                .map(contrato -> ResponseEntity.ok(convertToDTO(contrato)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContratoDTO> create(@Valid @RequestBody ContratoDTO contratoDTO) {
        Contrato contrato = convertToEntity(contratoDTO);
        Contrato savedContrato = contratoService.save(contrato);
        return ResponseEntity.ok(convertToDTO(savedContrato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contratoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empleado/{empleadoId}")
    public List<ContratoDTO> getByEmpleado(@PathVariable Long empleadoId) {
        return contratoService.findByEmpleadoId(empleadoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ContratoDTO convertToDTO(Contrato contrato) {
        return modelMapper.map(contrato, ContratoDTO.class);
    }

    private Contrato convertToEntity(ContratoDTO contratoDTO) {
        return modelMapper.map(contratoDTO, Contrato.class);
    }
}

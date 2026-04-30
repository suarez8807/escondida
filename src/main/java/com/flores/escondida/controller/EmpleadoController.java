package com.flores.escondida.controller;

import com.flores.escondida.dto.EmpleadoDTO;
import com.flores.escondida.entity.Empleado;
import com.flores.escondida.service.EmpleadoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<EmpleadoDTO> getAll() {
        return empleadoService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getById(@PathVariable Long id) {
        return empleadoService.findById(id)
                .map(empleado -> ResponseEntity.ok(convertToDTO(empleado)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola desde docker";
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> create(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        Empleado empleado = convertToEntity(empleadoDTO);
        Empleado savedEmpleado = empleadoService.save(empleado);
        return ResponseEntity.ok(convertToDTO(savedEmpleado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empleadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/documento/{numeroDocumento}")
    public ResponseEntity<EmpleadoDTO> getByDocumento(@PathVariable String numeroDocumento) {
        return empleadoService.findByNumeroDocumento(numeroDocumento)
                .map(empleado -> ResponseEntity.ok(convertToDTO(empleado)))
                .orElse(ResponseEntity.notFound().build());
    }

    private EmpleadoDTO convertToDTO(Empleado empleado) {
        return modelMapper.map(empleado, EmpleadoDTO.class);
    }

    private Empleado convertToEntity(EmpleadoDTO empleadoDTO) {
        return modelMapper.map(empleadoDTO, Empleado.class);
    }
}

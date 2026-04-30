package com.flores.escondida.config;

import com.flores.escondida.dto.ContratoDTO;
import com.flores.escondida.dto.PagoDTO;
import com.flores.escondida.dto.SalarioDTO;
import com.flores.escondida.entity.Contrato;
import com.flores.escondida.entity.Pago;
import com.flores.escondida.entity.Salario;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configuración para Contrato mapping (empleadoId -> empleado.id)
        modelMapper.typeMap(ContratoDTO.class, Contrato.class).addMappings(mapper -> {
            mapper.map(ContratoDTO::getEmpleadoId, (dest, v) -> dest.getEmpleado().setId((Long) v));
        });

        // Configuración inversa (empleado.id -> empleadoId)
        modelMapper.typeMap(Contrato.class, ContratoDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getEmpleado().getId(), ContratoDTO::setEmpleadoId);
        });

        // Configuración para Salario mapping (contratoId -> contrato.id)
        modelMapper.typeMap(SalarioDTO.class, Salario.class).addMappings(mapper -> {
            mapper.map(SalarioDTO::getContratoId, (dest, v) -> dest.getContrato().setId((Long) v));
        });

        modelMapper.typeMap(Salario.class, SalarioDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getContrato().getId(), SalarioDTO::setContratoId);
        });

        // Configuración para Pago mapping
        modelMapper.typeMap(PagoDTO.class, Pago.class).addMappings(mapper -> {
            mapper.map(PagoDTO::getContratoId, (dest, v) -> dest.getContrato().setId((Long) v));
            mapper.map(PagoDTO::getSalarioId, (dest, v) -> dest.getSalario().setId((Long) v));
        });

        modelMapper.typeMap(Pago.class, PagoDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getContrato().getId(), PagoDTO::setContratoId);
            mapper.map(src -> src.getSalario().getId(), PagoDTO::setSalarioId);
        });

        return modelMapper;
    }
}

package com.example.demo.usuario;

import com.example.demo.base.DTOConverter;
import org.springframework.stereotype.Component;

@Component
public class DTOConverterUsuario extends DTOConverter<Usuario, DTOUsuarioInput, DTOUsuarioUpdate, DTOUsuarioOutput> {
    @Override
    public DTOUsuarioOutput convertToDto(Usuario entity) {

        return new DTOUsuarioOutput(entity.getNombre());
    }

    @Override
    public Usuario convertToEntity(DTOUsuarioInput dto) {
        Usuario entity = new Usuario();
        entity.setNombre(dto.nombre());
        entity.setApellido(dto.apellido());
        entity.setDNI(dto.DNI());
        return entity;
    }

    @Override
    public Usuario updateEntity(DTOUsuarioUpdate dto, Usuario entity) {
        entity.setNombre(dto.nombre());
        entity.setApellido(dto.apellido());
        return entity;
    }
}

package com.example.demo.usuario;

import com.example.demo.base.*;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<Usuario, DTOUsuarioInput, DTOUsuarioOutput, DTOUsuarioUpdate> {

    @Override
    DTOUsuarioOutput toOutputDTO(Usuario entity);

    @Override
    Usuario toEntity(DTOUsuarioInput inputDTO);

    @Override
    Usuario updateEntity(DTOUsuarioUpdate updateDTO, @MappingTarget Usuario entity);
}



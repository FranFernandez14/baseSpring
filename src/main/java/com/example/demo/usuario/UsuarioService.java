package com.example.demo.usuario;

import com.example.demo.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService extends BaseService<Usuario, Long, DTOUsuarioInput, DTOUsuarioUpdate, DTOUsuarioOutput> {

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, DTOConverterUsuario dtoConverterUsuario) {
        super(usuarioRepository, dtoConverterUsuario);
    }
}


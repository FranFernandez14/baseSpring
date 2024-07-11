package com.example.demo.usuario;

import com.example.demo.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/usuarios")
public class UsuarioController extends BaseController<Usuario, UsuarioService, DTOUsuarioInput, DTOUsuarioUpdate, DTOUsuarioOutput> {

    @Autowired
    public UsuarioController(UsuarioService service) {
        super(service);
    }
}


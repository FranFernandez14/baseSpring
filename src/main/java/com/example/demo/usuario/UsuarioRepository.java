package com.example.demo.usuario;

import com.example.demo.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
}

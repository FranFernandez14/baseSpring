package com.example.demo.usuario;

import com.example.demo.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Usuario extends BaseEntity {
    private String nombre;
    private String apellido;
    private String DNI;
}

package com.example.demo.base;

public abstract class DTOConverter<E, I, U, O>{

    public abstract O convertToDto(E entity);

    public abstract E convertToEntity(I dto);

    public abstract E updateEntity(U dto, E entity);
}
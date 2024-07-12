package com.example.demo.base;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityMapper<E extends BaseEntity, I, O, U> {

    O toOutputDTO(E entity);

    E toEntity(I inputDTO);

    E updateEntity(U updateDTO, @MappingTarget E entity);

    default Page<O> toOutputPage(Page<E> entities) {
        List<O> outputList = entities.stream()
                .map(this::toOutputDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(outputList, entities.getPageable(), entities.getTotalElements());
    }
}


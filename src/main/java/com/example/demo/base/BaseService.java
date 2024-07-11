package com.example.demo.base;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseService<E extends BaseEntity, ID extends Serializable, I, U, O> {


    protected BaseRepository<E,ID> baseRepository;
    protected DTOConverter<E, I, U, O> dtoConverter;

    public BaseService(BaseRepository<E, ID> baseRepository, DTOConverter<E, I, U, O> dtoConverter) {
        this.baseRepository = baseRepository;
        this.dtoConverter = dtoConverter;
    }

    @Transactional
    public List<O> findAll() throws Exception {
        try {
            List<E> entities = baseRepository.findAll();
            return entities.stream().map(dtoConverter::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public O findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            return dtoConverter.convertToDto(entityOptional.orElseThrow(() -> new Exception("Entity not found")));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public O save(I inputDto) throws Exception {
        try {
            E entity = dtoConverter.convertToEntity(inputDto);
            entity = baseRepository.save(entity);
            return dtoConverter.convertToDto(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public O update(ID id, U updateDto) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.orElseThrow(() -> new Exception("Entity not found"));
            entityUpdate = dtoConverter.updateEntity(updateDto, entityUpdate);
            entityUpdate = baseRepository.save(entityUpdate);
            return dtoConverter.convertToDto(entityUpdate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean softDelete(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)) {
                E entity = baseRepository.findById(id).orElseThrow(() -> new Exception("Entity not found"));
                entity.setFechaBaja(new Date());
                baseRepository.save(entity);
                return true;
            } else {
                throw new Exception("Entity not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

package com.example.demo.base;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<E extends BaseEntity, ID extends Serializable, I, U, O> {

    protected BaseRepository<E, ID> baseRepository;
    protected EntityMapper entityMapper;

    public BaseService(BaseRepository<E, ID> baseRepository, EntityMapper entityMapper) {
        this.baseRepository = baseRepository;
        this.entityMapper = entityMapper;
    }

    @Transactional
    public Page<O> findAll(Pageable pageable) throws Exception {
        try {
            Page<E> entities = baseRepository.findByFechaBajaIsNull(pageable);
            return entityMapper.toOutputPage(entities);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Page<O> findDeleted(Pageable pageable) throws Exception {
        try {
            Page<E> entities = baseRepository.findByFechaBajaIsNotNull(pageable);
            return entityMapper.toOutputPage(entities);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public O findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            return (O) entityMapper.toOutputDTO(entityOptional.orElseThrow(() -> new Exception("Entity not found")));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public O save(I inputDto) throws Exception {
        try {
            E entity = (E) entityMapper.toEntity(inputDto);
            entity = baseRepository.save(entity);
            return (O) entityMapper.toOutputDTO(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public O update(ID id, U updateDto) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.orElseThrow(() -> new Exception("Entity not found"));
            entityUpdate = (E) entityMapper.updateEntity(updateDto, entityUpdate);
            entityUpdate = baseRepository.save(entityUpdate);
            return (O) entityMapper.toOutputDTO(entityUpdate);
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

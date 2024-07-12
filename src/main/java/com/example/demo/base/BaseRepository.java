package com.example.demo.base;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository <E extends BaseEntity,ID extends Serializable> extends JpaRepository <E,ID> {

    Page<E> findByFechaBajaIsNull(Pageable pageable);

    Page<E> findByFechaBajaIsNotNull(Pageable pageable);

}

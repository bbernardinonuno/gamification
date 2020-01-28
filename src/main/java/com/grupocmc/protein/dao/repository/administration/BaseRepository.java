package com.grupocmc.protein.dao.repository.administration;

import com.grupocmc.protein.dao.model.administration.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;


@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {

     boolean existsById(Long id);
}
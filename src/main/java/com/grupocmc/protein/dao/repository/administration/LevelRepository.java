package com.grupocmc.protein.dao.repository.administration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupocmc.protein.dao.model.administration.Level;

import java.util.List;

@Repository("levelRepository")
public interface LevelRepository extends BaseRepository<Level, Long>{

    Level findBySlug (String slug);

    List<Level> findByIdIn(List<Long> ids);
}

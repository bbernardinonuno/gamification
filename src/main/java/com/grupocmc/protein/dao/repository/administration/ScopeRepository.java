package com.grupocmc.protein.dao.repository.administration;

import com.grupocmc.protein.dao.model.administration.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("scopeRepository")
public interface ScopeRepository extends BaseRepository <Scope, Long> {

    Scope findBySlug (String slug);

    List<Scope> findByIdIn(List<Long> ids);
}

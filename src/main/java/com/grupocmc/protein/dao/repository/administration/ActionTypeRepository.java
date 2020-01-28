package com.grupocmc.protein.dao.repository.administration;

import com.grupocmc.protein.dao.model.administration.Action;
import com.grupocmc.protein.dao.model.administration.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("actionTypeRepository")
public interface ActionTypeRepository extends BaseRepository<ActionType, Long> {

    List<ActionType> findByIdIn(List<Long> ids);
}
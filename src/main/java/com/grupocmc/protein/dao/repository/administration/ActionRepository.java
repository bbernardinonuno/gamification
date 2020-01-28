package com.grupocmc.protein.dao.repository.administration;


import org.springframework.stereotype.Repository;
import com.grupocmc.protein.dao.model.administration.Action;

import java.util.List;

@Repository("actionRepository")
public interface ActionRepository  extends BaseRepository<Action, Long> {

    Action findBySlug(String slug);

    List<Action> findByIdIn(List<Long> ids);

    List<Action> findByActionParent(Action father);

    List<Action> findByActionParentIsNull();
}

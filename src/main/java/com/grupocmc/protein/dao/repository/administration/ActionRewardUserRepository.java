package com.grupocmc.protein.dao.repository.administration;

import com.grupocmc.protein.dao.model.administration.ActionRewardUser;
import com.grupocmc.protein.dao.model.administration.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("actionRewardUserRepository")
public interface ActionRewardUserRepository  extends BaseRepository<ActionRewardUser, Long> {

    List<ActionRewardUser> findByUserOrderByStartDateAsc(User user);
}

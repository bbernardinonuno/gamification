package com.grupocmc.protein.dao.repository.administration;

import com.grupocmc.protein.dao.model.administration.RewardType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rewardTypeRepository")
public interface RewardTypeRepository extends BaseRepository<RewardType, Long> {

    RewardType findBySlug(String slug);

    List<RewardType> findByIdIn(List<Long> ids);
}

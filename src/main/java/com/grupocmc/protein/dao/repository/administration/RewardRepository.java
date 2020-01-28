package com.grupocmc.protein.dao.repository.administration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupocmc.protein.dao.model.administration.Reward;

import java.util.List;

@Repository("rewardRepository")
public interface RewardRepository extends BaseRepository<Reward, Long>{

    Reward findBySlug(String slug);

    List<Reward> findByIdIn(List<Long> ids);
}

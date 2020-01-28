package com.grupocmc.protein.dao.repository.administration;

import com.grupocmc.protein.dao.model.administration.User;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends BaseRepository <User, Long> {

    User findByUsername (String username);
}

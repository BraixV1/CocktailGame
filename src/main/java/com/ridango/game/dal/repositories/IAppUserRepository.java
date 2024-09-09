package com.ridango.game.dal.repositories;

import com.ridango.game.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.annotation.Resource;

@Resource
public interface IAppUserRepository extends JpaRepository<User, Long> {

}

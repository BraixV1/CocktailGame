package com.ridango.game.dal.repositories;

import com.ridango.game.domain.Hint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHintRepository extends JpaRepository<Hint, Long> {
}

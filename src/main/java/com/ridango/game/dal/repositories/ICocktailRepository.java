package com.ridango.game.dal.repositories;

import com.ridango.game.domain.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICocktailRepository extends JpaRepository<Cocktail, Long> {
}

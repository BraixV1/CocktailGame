package com.ridango.game.dal.repositories;

import com.ridango.game.domain.GameCocktails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameCocktailsRepository extends JpaRepository<GameCocktails, Long> {

}

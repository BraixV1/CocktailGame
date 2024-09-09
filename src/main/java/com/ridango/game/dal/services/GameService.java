package com.ridango.game.dal.services;

import com.ridango.game.dal.services.CocktailService;
import com.ridango.game.dal.repositories.IGameRepository;
import com.ridango.game.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    private CocktailService cocktailService;

    @Transactional(readOnly = true)
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Transactional
    public Game createGame(Game game) {
        // Ensure currentCocktail is saved
        if (game.getCurrentCocktail() != null) {
            cocktailService.create(game.getCurrentCocktail());
        }
        return gameRepository.save(game);
    }

    @Transactional
    public Game updateGame(Game game) {
        Game existingGame = gameRepository.findById(game.getId()).orElse(null);
        if (existingGame != null) {
            existingGame.setScore(game.getScore());
            existingGame.setRevealedName(game.getRevealedName());
            existingGame.setTriesLeft(game.getTriesLeft());

            // Ensure currentCocktail is saved
            if (game.getCurrentCocktail() != null) {
                cocktailService.create(game.getCurrentCocktail());
                existingGame.setCurrentCocktail(game.getCurrentCocktail());
            }

            existingGame.setHint(game.getHint());
            existingGame.setUsedCocktails(game.getUsedCocktails());
            existingGame.setLastPlayedDt(new Date());
            return gameRepository.save(existingGame);
        }
        return null;
    }
}

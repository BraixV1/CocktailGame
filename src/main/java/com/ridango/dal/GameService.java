package com.ridango.dal;

import com.ridango.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private IGameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Game game) {
        Game existingGame = gameRepository.findById(game.getId()).orElse(null);
        if (existingGame != null) {
            existingGame.score = game.getScore();
            existingGame.revealedName = game.getRevealedName();
            existingGame.triesLeft = game.getTriesLeft();
            existingGame.currentCocktail = game.currentCocktail;
            existingGame.hint = game.hint;
            existingGame.usedCocktails = game.usedCocktails;
            existingGame.LastPlayedDt = new Date();
            return gameRepository.save(existingGame);
        }
        return null;
    }

}

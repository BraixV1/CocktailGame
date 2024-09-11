package com.ridango.game.dal.services;

import com.ridango.game.dal.repositories.IGameRepository;
import com.ridango.game.domain.Cocktail;
import com.ridango.game.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

@Service
public class GameService {

    private final IGameRepository gameRepository;

    private final CocktailService cocktailService;


    @Autowired
    public GameService(IGameRepository gameRepository, CocktailService cocktailService) {
        this.gameRepository = gameRepository;
        this.cocktailService = cocktailService;
    }


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
        if (game.getCurrentCocktail() == null) {
            throw new InputMismatchException("Game needs cocktail to be able to save");
        }
        if (game.getPlayer() == null) {
            throw new InputMismatchException("Game needs player to be able to save");
        }
        cocktailService.create(game.getCurrentCocktail());
        Game gottenGame = gameRepository.save(game);
        return gottenGame;
    }

    @Transactional
    public Game update(Game game) {
        Game existingGame = gameRepository.findById(game.getId()).orElse(null);
        if (existingGame == null){
            throw new InputMismatchException("Unable to update game that does not exist");
        }
        existingGame.setScore(game.getScore());
        existingGame.setRevealedName(game.getRevealedName());
        existingGame.setTriesLeft(game.getTriesLeft());
        if (game.getCurrentCocktail()  == null) {
            throw new ApplicationContextException("Game needs cocktail to be able to save");
        }
        if (cocktailService.get(game.getCurrentCocktail().getId()) == null) {
            Cocktail result =  cocktailService.create(game.getCurrentCocktail());
            existingGame.setCurrentCocktail(result);
        }
        existingGame.setHint(game.getHint());
        existingGame.setUsedCocktails(game.getUsedCocktails());
        existingGame.setLastPlayedDt(new Date());
        Game gottenGame =  gameRepository.save(existingGame);
        return gottenGame;
    }
}

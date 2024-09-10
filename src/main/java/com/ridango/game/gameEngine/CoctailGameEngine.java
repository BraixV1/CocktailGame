package com.ridango.game.gameEngine;

import com.ridango.game.cocktailService.CocktailEngine;
import com.ridango.game.dal.services.GameService;
import com.ridango.game.domain.Game;
import com.ridango.game.domain.GameCocktails;
import com.ridango.game.domain.Hint;
import com.ridango.game.hints.HintService;
import lombok.Getter;

import java.util.List;

public class CoctailGameEngine {

    private Game game;
    private GameService gameService;

    @Getter
    private Boolean isGameOver;



    public CoctailGameEngine(Game game, GameService gameService) throws Exception {
        this.game = game;
        this.VerifyGameIntegrity();
        this.gameService = gameService;
        this.isGameOver = false;

    }


    public void guessCocktail(String guess) throws Exception {

        if (game.getCurrentCocktail().strDrink.equalsIgnoreCase(guess)) {
            WinRound();
            return;
        }
        game.setTriesLeft(game.getTriesLeft() - 1);
        HintService.revealHint(game);
        Game found = gameService.getGameById(game.getId());
        if (found == null) {
            gameService.createGame(game);

        } else {
          gameService.update(game);
        }
        if (game.getTriesLeft() == 0) {
            isGameOver = true;
        }
    }

    public int getHealth() {
        return game.getTriesLeft();
    }

    public List<String> getHints(){
        return HintService.getAvailableHints(game);
    }

    public String getSecret() {
        return game.getRevealedName();
    }

    private void VerifyGameIntegrity() throws Exception {
        if (game.getHint() == null) {
            game.setHint(new Hint());
        }

        if (game.getCurrentCocktail() == null) {
            game.setCurrentCocktail(CocktailEngine.getRandomCocktail(game.getUsedCocktails()));
            game.setRevealedName(getSecretName(game.getCurrentCocktail().strDrink));
        }

        if (game.getPlayer() == null) {
            throw new Exception("Game has no user");
        }

    }

    private String getSecretName(String name) {
        return "_".repeat(name.length());
    }

    private void WinRound() throws Exception {
        game.setScore(game.getScore() + 1);
        GameCocktails usedCocktail = new GameCocktails();
        usedCocktail.setGame(game);
        usedCocktail.setCocktail(game.getCurrentCocktail());
        game.getUsedCocktails().add(usedCocktail);
        game.setCurrentCocktail(CocktailEngine.getRandomCocktail(game.getUsedCocktails()));
        game.setRevealedName(getSecretName(game.getCurrentCocktail().strDrink));
        game.getHint().resetHint();
    }





}

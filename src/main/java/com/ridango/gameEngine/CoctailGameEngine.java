package com.ridango.gameEngine;

import com.ridango.cocktailService.CocktailService;
import com.ridango.domain.Game;
import com.ridango.domain.GameCocktails;
import com.ridango.domain.Hint;
import com.ridango.hints.HintService;

import java.util.Date;

public class CoctailGameEngine {

    private Game game;


    public CoctailGameEngine(Game game) throws Exception {
        this.game = game;
        this.VerifyGameIntegrity();
    }

    public boolean guessCocktail(String guess) throws Exception {

        if (game.currentCocktail.strDrink.equalsIgnoreCase(guess)) {
            WinRound();
            return true;
        }
        game.triesLeft--;
        HintService.revealHint(game);
        return false;
    }

    private void VerifyGameIntegrity() throws Exception {
        if (game.hint == null) {
            game.hint = new Hint();
        }

        if (game.currentCocktail == null) {
            game.currentCocktail = CocktailService.getRandomCocktail(game.usedCocktails);
            game.revealedName = getSecretName(game.currentCocktail.strDrink);
        }

        if (game.Player == null) {
            throw new Exception("Game has no user");
        }

    }

    private String getSecretName(String name) {
        return "_".repeat(name.length());
    }

    private void WinRound() throws Exception {
        game.score++;
        GameCocktails usedCocktail = new GameCocktails();
        usedCocktail.game = game;
        usedCocktail.coctail = game.currentCocktail;
        game.usedCocktails.add(usedCocktail);
        game.currentCocktail = CocktailService.getRandomCocktail(game.usedCocktails);
        game.revealedName = getSecretName(game.currentCocktail.strDrink);
        game.hint = new Hint();
    }





}

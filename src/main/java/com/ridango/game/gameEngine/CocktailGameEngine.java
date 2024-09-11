package com.ridango.game.gameEngine;

import com.ridango.game.cocktailService.CocktailEngine;
import com.ridango.game.dal.services.GameService;
import com.ridango.game.domain.Game;
import com.ridango.game.domain.GameCocktails;
import com.ridango.game.domain.Hint;
import com.ridango.game.hints.HintService;
import lombok.Getter;

import java.util.List;

public class CocktailGameEngine {

    private Game game;
    private GameService gameService;

    @Getter
    private Game bestGame;

    @Getter
    private Boolean isGameOver;



    public CocktailGameEngine(Game game, GameService gameService) throws Exception {
        this.game = game;
        this.VerifyGameIntegrity();
        this.gameService = gameService;
        this.isGameOver = false;
        SetupBestGame();
    }


    public void guessCocktail(String guess) throws Exception {

        if (!allHintsGiven() && guess.trim().equalsIgnoreCase("skip")) {
            HintService.revealHint(game);
        }
        else if (game.getCurrentCocktail().strDrink.equalsIgnoreCase(guess.trim())) {
            WinRound();
            return;
        } else {
            game.setTriesLeft(game.getTriesLeft() - 1);
            HintService.revealHint(game);
        }
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


    private void VerifyGameIntegrity() throws Exception {
        if (game.getHint() == null) {
            game.setHint(new Hint());
        }

        if (game.getCurrentCocktail() == null) {
            game.setCurrentCocktail(CocktailEngine.getRandomCocktail(game.getUsedCocktails()));
        }
        game.setRevealedName(getSecretName(game.getCurrentCocktail().strDrink));

        if (game.getPlayer() == null) {
            throw new Exception("Game has no user");
        }

        if (game.getPlayer().getName() == null) {
            throw new Exception("Player does not have a name");
        }


    }

    private String getSecretName(String name) {
        return "_".repeat(name.length());
    }

    private void WinRound() throws Exception {
        game.setScore(game.getScore() + game.getTriesLeft());
        GameCocktails usedCocktail = new GameCocktails();
        usedCocktail.setGame(game);
        usedCocktail.setCocktail(game.getCurrentCocktail());
        game.getUsedCocktails().add(usedCocktail);
        game.setCurrentCocktail(CocktailEngine.getRandomCocktail(game.getUsedCocktails()));
        game.setRevealedName(getSecretName(game.getCurrentCocktail().strDrink));
        game.getHint().resetHint();
    }

    public int getHealth() {
        return game.getTriesLeft();
    }

    public List<String> getHints(){
        return HintService.getAvailableHints(game);
    }

    public boolean allHintsGiven() {
        int count = 0;

        for (int i = 0; i < game.getRevealedName().length(); i++) {
            if (game.getRevealedName().charAt(i) == '_') {
                count++;
            }
        }
        return game.getHint().AllHintsRevealed() && count <= 3;
    }

    public String getSecret() {
        return game.getRevealedName();
    }

    public int getScore() {return game.getScore();}

    private void SetupBestGame() {
        List<Game> allGames = this.gameService.getAllGames();
        allGames.sort((game1, game2) -> game2.getScore() - game1.getScore());
        if (!allGames.isEmpty()) {
            bestGame = allGames.get(0);
        }
    }


}

package com.ridango.game.consoleUI;

import com.ridango.game.domain.Game;
import com.ridango.game.gameEngine.CocktailGameEngine;

import java.util.List;
import java.util.Scanner;

public class GameController {

    private final CocktailGameEngine gameEngine;

    public GameController(CocktailGameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }


    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (!gameEngine.getIsGameOver()) {
            displayGameStatus();
            System.out.print("Name of the cocktail: ");
            String guess = scanner.nextLine();
            gameEngine.guessCocktail(guess);
        }
    }

    private void displayGameStatus() {
        if (gameEngine.getBestGame() != null) {
            System.out.println("Highest score: " + gameEngine.getBestGame().getScore());
        }
        System.out.println("Tries left: " + gameEngine.getHealth());
        System.out.println("CurrentScore: " + gameEngine.getScore());
        List<String> hints = gameEngine.getHints();
        for (String hint : hints) {
            System.out.println("Hint: " + hint);
        }
        System.out.println("==========================");
        System.out.println("RESULT: " + gameEngine.getSecret());
        System.out.println("==========================");
    }

}

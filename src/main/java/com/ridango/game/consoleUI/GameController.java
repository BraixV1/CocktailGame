package com.ridango.game.consoleUI;

import com.ridango.game.gameEngine.CoctailGameEngine;

import java.util.List;
import java.util.Scanner;

public class GameController {

    private final CoctailGameEngine gameEngine;


    public GameController(CoctailGameEngine gameEngine) {
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
        
        System.out.println("Tries left: " + gameEngine.getHealth());
        List<String> hints = gameEngine.getHints();
        for (String hint : hints) {
            System.out.println("Hint: " + hint);
        }
        System.out.println("==========================");
        System.out.println("RESULT: " + gameEngine.getSecret());
        System.out.println("==========================");
    }

}

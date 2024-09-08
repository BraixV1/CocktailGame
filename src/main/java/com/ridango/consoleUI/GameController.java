package com.ridango.consoleUI;

import com.ridango.gameEngine.CoctailGameEngine;

import java.util.List;
import java.util.Scanner;

public class GameController {

    private final CoctailGameEngine gameEngine;


    public GameController(CoctailGameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }




    public void run() throws Exception {

        do {
            List<String> hints = gameEngine.getHints();
            System.out.println("Tries left: " + gameEngine.getHealth());
            for (String hint : hints) {
                System.out.println("Hint: " + hint);
            }
            System.out.println("==========================");
            System.out.println(gameEngine.getSecret());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Name of the cocktail: ");
            String guess = scanner.nextLine();

            gameEngine.guessCocktail(guess);
        } while (gameEngine.getHealth() != 0);

        System.out.println("GAME OVER");
        System.exit(0);

    }
}

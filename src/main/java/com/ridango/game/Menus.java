package com.ridango.game;

import com.ridango.menu.Menu;
import com.ridango.menu.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class Menus {



    public static Menu getMainMenu(Function<Void, String> newGameMethod) {
        return new Menu(">>>>>>>>> GUESS THE COCKTAIL <<<<<<<<<<<", new ArrayList<>(Arrays.asList(
                new MenuItem("s", (Void) -> "start new game", newGameMethod),
                new MenuItem("x", (Void) -> "Exit the game", (Void) -> { System.exit(0); return ""; })
        )));
    }




}

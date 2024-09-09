package com.ridango.game;

import com.ridango.game.domain.menu.Menu;
import com.ridango.game.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class Menus {



    public static Menu getMainMenu(Function<Void, String> newGameMethod) {
        return new Menu(">>>>>>>>> GUESS THE COCKTAIL <<<<<<<<<<<", new ArrayList<>(Arrays.asList(
                new MenuItem("s",  "start new game", newGameMethod)
        )));
    }




}

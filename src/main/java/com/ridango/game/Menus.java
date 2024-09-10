package com.ridango.game;

import com.ridango.game.dal.services.GameService;
import com.ridango.game.domain.Game;
import com.ridango.game.menu.EMenuLevel;
import com.ridango.game.menu.Menu;
import com.ridango.game.menu.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Menus {


    public static Menu getMainMenu(Function<Void, String> newGameMethod, GameService service) {
        Menu mainMenu = new Menu(">>>>>>>>> GUESS THE COCKTAIL <<<<<<<<<<<", new ArrayList<>());

        mainMenu.setMenuItems(new HashMap<String, MenuItem>() {{
            put("s", new MenuItem("s", "start new game", newGameMethod));
            put("l", new MenuItem("l", "show leaderboard", (Void) -> {
                Menu leaderboardsMenu = getLeaderBoardMenu(service.getAllGames(), mainMenu);
                leaderboardsMenu.Run(EMenuLevel.Second);
                return "";
            }));
        }});

        return mainMenu;
    }

    public static Menu getLeaderBoardMenu(List<Game> games, Menu mainMenu) {
        games.sort((game1, game2) -> game2.getScore() - game1.getScore());

        for (int i = 0; i < games.size(); i++) {
            Game current = games.get(i);
            System.out.println("#" + (i + 1) + " Player: " + current.getPlayer().getName() + " Score: " + current.getScore());
        }

        return new Menu("Leaderboard", mainMenu, new ArrayList<>(Arrays.asList(
        )));
    }
}

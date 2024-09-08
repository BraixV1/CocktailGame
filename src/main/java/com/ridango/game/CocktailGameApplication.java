package com.ridango.game;

import com.ridango.cocktailService.CocktailService;
import com.ridango.consoleUI.GameController;
import com.ridango.domain.Cocktail;
import com.ridango.domain.Game;
import com.ridango.domain.User;
import com.ridango.gameEngine.CoctailGameEngine;
import com.ridango.menu.Menu;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;


@SpringBootApplication
@Log
public class CocktailGameApplication implements CommandLineRunner {



	public CocktailGameApplication(final DataSource dataSource) {}
	public static void main(String[] args) {

		SpringApplication.run(CocktailGameApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Menu mainMenu = Menus.getMainMenu()
        try {


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


	}

	public void newGame(Game game) throws Exception {
		CoctailGameEngine gameEngine = new CoctailGameEngine(game);

		GameController controller = new GameController(gameEngine);

		controller.run();
	}

}

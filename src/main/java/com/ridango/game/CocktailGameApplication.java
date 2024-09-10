package com.ridango.game;

import com.ridango.game.consoleUI.GameController;
import com.ridango.game.dal.services.*;
import com.ridango.game.domain.Game;
import com.ridango.game.domain.User;
import com.ridango.game.gameEngine.CoctailGameEngine;
import com.ridango.game.menu.Menu;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.util.Scanner;
import java.util.function.Function;

@SpringBootApplication
@Log
public class CocktailGameApplication implements CommandLineRunner {

	private final DataSource dataSource;
	private final GameService gameService;
	private final CocktailService cocktailService;

	public CocktailGameApplication(DataSource dataSource, GameService gameService, CocktailService cocktailService) {
		this.dataSource = dataSource;
		this.gameService = gameService;
        this.cocktailService = cocktailService;
    }

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CocktailGameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu mainMenu = Menus.getMainMenu(newGame( gameService), gameService);
		mainMenu.Run();
		System.exit(0);
	}

	public Function<Void, String> newGame(GameService gameService) {
		return (Void) -> {
			try {
				Game game = new Game();
				User user = new User();
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter your name: ");
				user.setName(scanner.nextLine());
				game.setPlayer(user);

				CoctailGameEngine gameEngine = new CoctailGameEngine(game, gameService);
				GameController controller = new GameController(gameEngine);
				controller.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		};
	}
}

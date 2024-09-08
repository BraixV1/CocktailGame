package com.ridango.game;

import com.ridango.consoleUI.GameController;
import com.ridango.domain.Game;
import com.ridango.domain.User;
import com.ridango.gameEngine.CoctailGameEngine;
import com.ridango.menu.EMenuLevel;
import com.ridango.menu.Menu;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.util.Scanner;
import java.util.function.Function;


@SpringBootApplication
@Log
public class CocktailGameApplication implements CommandLineRunner {



	public CocktailGameApplication(final DataSource dataSource) {}
	public static void main(String[] args) {

		SpringApplication.run(CocktailGameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Game game = new Game();

		Menu mainMenu = Menus.getMainMenu(newGame(game));

		mainMenu.Run();
		return;

	}

	public Function<Void, String> newGame(Game game) {
		return (Void) -> {
			try {
				User user = new User();
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter your name: ");
				user.setName(scanner.nextLine());
				game.setPlayer(user);

				CoctailGameEngine gameEngine = new CoctailGameEngine(game);
				GameController controller = new GameController(gameEngine);
				controller.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		};
	}


}

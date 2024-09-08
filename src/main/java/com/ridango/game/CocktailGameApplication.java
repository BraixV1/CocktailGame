package com.ridango.game;

import com.ridango.cocktailService.CocktailService;
import com.ridango.domain.Cocktail;
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


	private final DataSource dataSource;

	public CocktailGameApplication(final DataSource dataSource) {this.dataSource = dataSource;}
	public static void main(String[] args) {

		SpringApplication.run(CocktailGameApplication.class, args);
	}

	@Override
	public void run(String... args) {

        try {
            Cocktail cocktail = CocktailService.getRandomCocktail(new ArrayList<>());
			System.out.println(cocktail.strDrink);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Datasource: " + dataSource.toString());

		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
		restTemplate.execute("select 1");

	}
}

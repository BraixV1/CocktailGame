package com.ridango.dao;

import com.ridango.game.CocktailGameApplication;
import com.ridango.game.cocktailService.CocktailEngine;
import com.ridango.game.dal.repositories.ICocktailRepository;
import com.ridango.game.dal.repositories.IGameRepository;
import com.ridango.game.dal.services.CocktailService;
import com.ridango.game.dal.services.GameService;
import com.ridango.game.domain.Cocktail;
import com.ridango.game.domain.Game;
import com.ridango.game.domain.Hint;
import com.ridango.game.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CocktailGameApplication.class)
public class GameTest {

    @MockBean
    private IGameRepository gameRepository;

    @MockBean
    private ICocktailRepository cocktailRepository;

    @Autowired
    private CocktailService cocktailService;

    @Autowired
    private GameService gameService;

    @Test
    void testCreateGameSuccess() throws Exception {
        // Arrange
        Game game = new Game();
        User user = new User();
        user.setName("Ridango");
        Cocktail cocktail = new Cocktail();
        game.setPlayer(user);
        game.setCurrentCocktail(cocktail);

        List<Game> gamesBefore = new ArrayList<>();
        when(gameRepository.findAll()).thenReturn(gamesBefore);

        int allGameSizeBefore = gameService.getAllGames().size();

        // Act
        gameService.createGame(game);

        List<Game> gamesAfter = new ArrayList<>(gamesBefore);
        gamesAfter.add(game);
        when(gameRepository.findAll()).thenReturn(gamesAfter);

        int allGamesSizeAfter = gameService.getAllGames().size();

        // Assert
        assertEquals(allGameSizeBefore + 1, allGamesSizeAfter);
        verify(gameRepository, times(2)).findAll();
    }


    @Test
    void testCreateGameFailureWithoutPerson() throws Exception {
        // Arrange
        Game game = new Game();
        Cocktail cocktail = CocktailEngine.getRandomCocktail(game.getUsedCocktails());
        game.setCurrentCocktail(cocktail);

        // Act & Assert
        assertThrows(InputMismatchException.class, () -> {
            gameService.createGame(game);
        });
    }

    @Test void testCreateGameFailureWithCocktail() throws Exception {
       // Arrange
        Game game = new Game();
        User user = new User();
        user.setName("Ridango");
        game.setPlayer(user);

        // Act & Assert
        assertThrows(InputMismatchException.class, () -> {
            gameService.createGame(game);
        });
    }

    @Test void testUpdateGameThatDoesNotExist() throws Exception {
        // Arrange
        Game game = new Game();

        // Act & Assert
        assertThrows(InputMismatchException.class, () -> {
            gameService.update(game);
        });
    }


}

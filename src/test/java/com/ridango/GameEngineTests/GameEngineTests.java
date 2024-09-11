package com.ridango.GameEngineTests;

import com.ridango.game.CocktailGameApplication;
import com.ridango.game.dal.repositories.ICocktailRepository;
import com.ridango.game.dal.repositories.IGameRepository;
import com.ridango.game.dal.services.CocktailService;
import com.ridango.game.dal.services.GameService;
import com.ridango.game.domain.Cocktail;
import com.ridango.game.domain.Game;
import com.ridango.game.domain.User;
import com.ridango.game.gameEngine.CocktailGameEngine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CocktailGameApplication.class)
public class GameEngineTests {

    @MockBean
    private IGameRepository gameRepository;

    @MockBean
    private ICocktailRepository cocktailRepository;

    @Autowired
    private CocktailService cocktailService;

    @Autowired
    private GameService gameService;

    private Game setupGame(String playerName, String cocktailName, Integer cocktailId) {
        Game game = new Game();
        User user = new User();
        user.setName(playerName);
        game.setPlayer(user);

        if (cocktailName != null && cocktailId != null) {
            Cocktail cocktail = new Cocktail();
            cocktail.strDrink = cocktailName;
            cocktail.idDrink = cocktailId;
            game.setCurrentCocktail(cocktail);
        }

        return game;
    }

    @Test
    void loadContext() {
        // Context load test
    }

    @Test
    void CreateGame() throws Exception {
        Game game = setupGame("Ridango", null, null);

        CocktailGameEngine engine = new CocktailGameEngine(game, gameService);

        assertFalse(engine.getIsGameOver());
    }

    @Test
    void CreateGameFails() throws Exception {
        Game game = setupGame(null, null, null);

        assertThrows(Exception.class, () -> {
            new CocktailGameEngine(game, gameService);
        }, "Game engine should fail because game has no user");
    }

    @Test
    void GuessWrongHealthCheck() throws Exception {
        Game game = setupGame("Ridango", "Answer", 3453);

        CocktailGameEngine engine = new CocktailGameEngine(game, gameService);
        int healthBefore = engine.getHealth();
        engine.guessCocktail("Guess");
        int healthAfter = engine.getHealth();

        assertEquals(healthBefore - 1, healthAfter, "Guessing wrong does not lower health by one");
    }

    @Test
    void GuessRightScoreCheck() throws Exception {
        Game game = setupGame("Ridango", "Answer", 3453);

        CocktailGameEngine engine = new CocktailGameEngine(game, gameService);
        int scoreBefore = engine.getScore();
        engine.guessCocktail("Answer");
        int scoreAfter = engine.getScore();

        assertEquals(scoreBefore + 1, scoreAfter, "Guessing right does not increment score by one");
    }

    @Test
    void RevealLetterTest() throws Exception {
        Game game = setupGame("Ridango", "Answer", 3453);

        CocktailGameEngine engine = new CocktailGameEngine(game, gameService);
        String beforeGuess = engine.getSecret();

        engine.guessCocktail("Wrong");
        String afterGuess = engine.getSecret();

        assertNotEquals(beforeGuess, afterGuess, "Guessing wrong did not reveal letter");
    }
}

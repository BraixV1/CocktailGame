package com.ridango.cocktailService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridango.domain.Cocktail;
import com.ridango.domain.GameCocktails;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class CocktailService {

    private static final String URL = "https://www.thecocktaildb.com/api/json/v1/1/random.php";


    public static Cocktail getRandomCocktail(List<GameCocktails> used) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        int failCount = 0;
        boolean isUnique;

        Cocktail cocktail = null;

        do {
            String response = restTemplate.getForObject(URL, String.class);
            JsonNode rootNode = mapper.readTree(response);

            JsonNode drinksNode = rootNode.get("drinks");
            if (drinksNode != null && drinksNode.isArray()) {
                JsonNode drinkNode = drinksNode.get(0);
                int id = drinkNode.get("idDrink").asInt();

                isUnique = used.stream().noneMatch(gameCocktail -> gameCocktail.getCoctail().idDrink == id);

                if (isUnique) {
                    cocktail = mapper.treeToValue(drinkNode, Cocktail.class);
                    break;
                }
            }

            failCount++;
        } while (failCount < 5);

        if (failCount == 5) {
            throw new Exception("Fail count exceeded please check if api works or that there are possible drinks left in the api");
        }

        return cocktail;
    }

}

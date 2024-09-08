package com.ridango.hints;

import com.ridango.domain.Cocktail;
import com.ridango.domain.Game;
import com.ridango.domain.Hint;

import java.lang.reflect.Field;
import java.util.*;

public class HintService {

    private static final Random rand = new Random();

    public static void revealHint(Game game) {

        if (game.currentCocktail == null) {
            System.out.println("No coctail found");
            return;
        }
        if (game.hint == null) {
            System.out.println("No hint found");
            return;
        }

        initializeHints(game.currentCocktail, game.hint);
        List<String> possibleHints = getPossibleHints(game.currentCocktail, game.hint);

        if (canGetNameHint(game.currentCocktail.strDrink, game.revealedName)) {
            possibleHints.add("RevealName");
        }

        if (!possibleHints.isEmpty()) {
            String chosenHint = possibleHints.get(rand.nextInt(possibleHints.size()));

            if (chosenHint.equals("RevealName")) {
                game.revealedName = revealLetter(game.currentCocktail.strDrink, game.revealedName);
            } else {
                setHintToTrue(game.hint, chosenHint);
            }
        }
    }

    private static void initializeHints(Cocktail cocktail, Hint hints) {
        Map<String, String> fieldHintMap = getFieldHintMap();

        for (Map.Entry<String, String> entry : fieldHintMap.entrySet()) {
            String cocktailFieldName = entry.getKey();
            String hintFieldName = entry.getValue();

            try {
                Field cocktailField = Cocktail.class.getDeclaredField(cocktailFieldName);
                cocktailField.setAccessible(true);
                String fieldValue = (String) cocktailField.get(cocktail);

                if (fieldValue == null || fieldValue.isEmpty()) {
                    Field hintField = Hint.class.getDeclaredField(hintFieldName);
                    hintField.setAccessible(true);
                    hintField.setBoolean(hints, true);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> getAvailableHints(Game game) {
        List<String> availableHints = new ArrayList<>();
        Map<String, String> fieldHintMap = getFieldHintMap();

        availableHints.add(game.currentCocktail.strInstructions);

        for (Map.Entry<String, String> entry : fieldHintMap.entrySet()) {
            String cocktailFieldName = entry.getKey();
            String hintFieldName = entry.getValue();

            try {
                Field hintField = Hint.class.getDeclaredField(hintFieldName);
                hintField.setAccessible(true);

                boolean hintUsed = hintField.getBoolean(game.getHint());

                if (hintUsed) {
                    Field cocktailField = Cocktail.class.getDeclaredField(cocktailFieldName);
                    cocktailField.setAccessible(true);
                    String fieldValue = (String) cocktailField.get(game.getCurrentCocktail());


                    if (fieldValue != null && !fieldValue.isEmpty()) {
                        availableHints.add(fieldValue);
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return availableHints;
    }


    private static List<String> getPossibleHints(Cocktail cocktail, Hint hints) {
        List<String> possibleHints = new ArrayList<>();
        Map<String, String> fieldHintMap = getFieldHintMap();

        for (Map.Entry<String, String> entry : fieldHintMap.entrySet()) {
            String cocktailFieldName = entry.getKey();
            String hintFieldName = entry.getValue();

            try {
                Field hintField = Hint.class.getDeclaredField(hintFieldName);
                hintField.setAccessible(true);
                boolean hintUsed = hintField.getBoolean(hints);

                if (!hintUsed) {
                    Field cocktailField = Cocktail.class.getDeclaredField(cocktailFieldName);
                    cocktailField.setAccessible(true);
                    String fieldValue = (String) cocktailField.get(cocktail);

                    if (fieldValue != null && !fieldValue.isEmpty()) {
                        possibleHints.add(cocktailFieldName);
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return possibleHints;
    }

    private static void setHintToTrue(Hint hints, String cocktailFieldName) {
        try {
            String hintFieldName = "show" + cocktailFieldName.substring(0, 1).toUpperCase() + cocktailFieldName.substring(1);
            Field hintField = Hint.class.getDeclaredField(hintFieldName);
            hintField.setAccessible(true);
            hintField.setBoolean(hints, true);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static boolean canGetNameHint(String cocktailName, String revealedName) {
        return getNotRevealedCharactersIndexes(revealedName).size() >= 3;
    }

    private static List<Integer> getNotRevealedCharactersIndexes(String revealedName) {
        List<Integer> revealedCharactersIndexes = new ArrayList<>();
        char[] characterList = revealedName.toCharArray();

        for (int i = 0; i < characterList.length; i++) {
            if (characterList[i] == '_') {
                revealedCharactersIndexes.add(i);
            }
        }

        return revealedCharactersIndexes;
    }

    private static String revealLetter(String fullName, String revealedName) {
        List<Integer> indexes = getNotRevealedCharactersIndexes(revealedName);
        int index = rand.nextInt(indexes.size());
        char character = fullName.charAt(indexes.get(index));

        char[] revealedNameCharArray = revealedName.toCharArray();
        revealedNameCharArray[indexes.get(index)] = character;

        return new String(revealedNameCharArray);
    }

    private static Map<String, String> getFieldHintMap() {
        Map<String, String> map = new HashMap<>();
        map.put("strDrinkThumb", "showStrDrinkThumb");
        map.put("strIngredient1", "showStrIngredient1");
        map.put("strIngredient2", "showStrIngredient2");
        map.put("strIngredient3", "showStrIngredient3");
        map.put("strIngredient4", "showStrIngredient4");
        map.put("strIngredient5", "showStrIngredient5");
        map.put("strIngredient6", "showStrIngredient6");
        map.put("strIngredient7", "showStrIngredient7");
        map.put("strIngredient8", "showStrIngredient8");
        map.put("strIngredient9", "showStrIngredient9");
        map.put("strIngredient10", "showStrIngredient10");
        map.put("strIngredient11", "showStrIngredient11");
        map.put("strIngredient12", "showStrIngredient12");
        map.put("strIngredient13", "showStrIngredient13");
        map.put("strIngredient14", "showStrIngredient14");
        map.put("strIngredient15", "showStrIngredient15");
        return map;
    }
}
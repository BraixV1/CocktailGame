package com.ridango.hints;

import com.ridango.domain.Cocktail;
import com.ridango.domain.Game;
import com.ridango.domain.Hint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HintService {

    private static final Integer minimumCharactersConst = 3;
    private static final Random rand = new Random();



    public static void RevealHint(Cocktail cocktail, Hint hints, Game game) {


        List<String> possibleHints = getPossibleHints(cocktail, hints, game);
        int maxHintIndex = possibleHints.size();
        int pickedHint;

        if (canGetNameHint(game.currentCoctail.strDrink, game.revealedName)) {
            pickedHint = rand.nextInt(maxHintIndex + 1); // Allow an extra option for revealing a letter
        } else {
            pickedHint = rand.nextInt(maxHintIndex); // Only pick from the available hints
        }
        if (pickedHint == possibleHints.size()) {
            game.revealedName = revealLetter(game.currentCoctail.strDrink, game.revealedName);
        }

    }

    private static List<String> getPossibleHints(Cocktail cocktail, Hint hint, Game game) {

        List<String> possibleHints = new ArrayList<>();

        if(!hint.showStrDrinkThumb && !cocktail.strDrinkThumb.isEmpty()){
            possibleHints.add(cocktail.strDrinkThumb);
        }
        if(!hint.showStrInstructions && !cocktail.strInstructions.isEmpty()){
            possibleHints.add(cocktail.strInstructions);
        }
        if(!hint.showStrIngredient1 && !cocktail.strIngredient1.isEmpty()){
            possibleHints.add(cocktail.strIngredient1);
        }
        if (!hint.showStrIngredient2 && !cocktail.strIngredient2.isEmpty()){
            possibleHints.add(cocktail.strIngredient2);
        }
        if(!hint.showStrIngredient3 && !cocktail.strIngredient3.isEmpty()){
            possibleHints.add(cocktail.strIngredient3);
        }
        if (!hint.showStrIngredient4 && !cocktail.strIngredient4.isEmpty()){
            possibleHints.add(cocktail.strIngredient4);
        }
        if(!hint.showStrIngredient5 && !cocktail.strIngredient5.isEmpty()){
            possibleHints.add(cocktail.strIngredient5);
        }
        if (!hint.showStrIngredient6 && !cocktail.strIngredient6.isEmpty()){
            possibleHints.add(cocktail.strIngredient6);
        }
        if(!hint.showStrIngredient7 && !cocktail.strIngredient7.isEmpty()){
            possibleHints.add(cocktail.strIngredient7);
        }
        if (!hint.showStrIngredient8 && !cocktail.strIngredient8.isEmpty()){
            possibleHints.add(cocktail.strIngredient8);
        }
        if(!hint.showStrIngredient9 && !cocktail.strIngredient9.isEmpty()){
            possibleHints.add(cocktail.strIngredient9);
        }
        if(!hint.showStrIngredient10 && !cocktail.strIngredient10.isEmpty()){
            possibleHints.add(cocktail.strIngredient10);
        }
        if(!hint.showStrIngredient11 && !cocktail.strIngredient11.isEmpty()){
            possibleHints.add(cocktail.strIngredient11);
        }
        if (!hint.showStrIngredient12 && !cocktail.strIngredient12.isEmpty()){
            possibleHints.add(cocktail.strIngredient12);
        }
        if(!hint.showStrIngredient13 && !cocktail.strIngredient13.isEmpty()){
            possibleHints.add(cocktail.strIngredient13);
        }
        if (!hint.showStrIngredient14 && !cocktail.strIngredient14.isEmpty()){
            possibleHints.add(cocktail.strIngredient14);
        }
        if(!hint.showStrIngredient15 && !cocktail.strIngredient15.isEmpty()){
            possibleHints.add(cocktail.strIngredient15);
        }
        return possibleHints;
    }

    public static boolean canGetNameHint(String cocktailName, String revealedName) {

        List<Integer> notRevealedCharacters = getNotRevealedCharactersIndexes(revealedName);

        return notRevealedCharacters.size() <= 3;

    }

    public static List<Integer> getNotRevealedCharactersIndexes(String revealedName) {
        List<Integer> revealedCharactersIndexes = new ArrayList<>();
        char[] characterList = revealedName.toCharArray();

        for (int i = 0; i < characterList.length; i++) {
            if (characterList[i] != '_') continue;
            revealedCharactersIndexes.add(i);
        }
        return revealedCharactersIndexes;

    }

    public static String revealLetter(String fullName, String revealedName) {
        List<Integer> indexes = getNotRevealedCharactersIndexes(revealedName);

        int index = rand.nextInt(indexes.size());

        char character = fullName.charAt(indexes.get(index));

        char[] revealedNameCharArray = revealedName.toCharArray();

        revealedNameCharArray[indexes.get(index)] = character;

        StringBuilder builder = new StringBuilder();

        for (char c : revealedNameCharArray) {
            builder.append(c);
        }

        return builder.toString();

    }

}

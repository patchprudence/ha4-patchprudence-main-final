package htw.berlin.wi.prog2.data;

import htw.berlin.wi.prog2.domain.Ingredient;

import java.util.*;

public class MenuUtils {

    public static List<String> focusOnNames(Map<Long, Ingredient> articles) {
        List<String> names = new ArrayList<>();
        if (articles != null) {
            for (Ingredient art : articles.values()) names.add(art.getName());
        }
        return names;
    }

    public static Map<String, Long> focusOnNameAndInvert(Map<Long, Ingredient> articles) {

        Map<String, Long> inverted = new HashMap<>();

        if (articles != null) {

            for (Map.Entry<Long, Ingredient> entry : articles.entrySet()) {

                inverted.put(entry.getValue().getName(), entry.getKey());

            }

        }

        return inverted;

    }

    public static List<Ingredient> ingredientsFromIdAndCount(Map<Long, Integer> idsAndCount, Map<Long, Ingredient> articles) {

        List<Ingredient> ingredients = new LinkedList<>();

        if (idsAndCount != null && articles != null) {

            for (Map.Entry<Long, Integer> entry : idsAndCount.entrySet()) {

                Ingredient ingredient = articles.get(entry.getKey());

                for (int i = 0; i < entry.getValue(); i++) {

                    ingredients.add(ingredient);

                }

            }

        }

        return ingredients;

    }
}

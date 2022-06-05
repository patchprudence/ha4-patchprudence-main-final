package htw.berlin.wi.prog2.ui;

import htw.berlin.wi.prog2.data.Menu;
import htw.berlin.wi.prog2.data.MenuUtils;
import htw.berlin.wi.prog2.domain.Ingredient;
import htw.berlin.wi.prog2.domain.Pizza;
import htw.berlin.wi.prog2.parsing.ExtendableInputParser;
import htw.berlin.wi.prog2.domain.PizzaBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class CommandLineUI {
    private final UserInputWrapper input;
    private final PizzaBuilder builder;
    private final ExtendableInputParser parser;

    public CommandLineUI(UserInputWrapper uiw, PizzaBuilder sb, ExtendableInputParser ip) {
        input = uiw;
        builder = sb;
        parser = ip;
    }

    public String launch() {

        String inputLine = input.ask("Willkommen beim Pizza-Bot! Was möchtest du gerne bestellen?");

        Map<Long, Ingredient> articles = Menu.getAllArticles();

        while (!(inputLine.equals("Bestellung abschliessen") || inputLine.equals("Auf Wiedersehen"))) {
            Map<String, Long> keywordsToIds = MenuUtils.focusOnNameAndInvert(articles);
            Map<Long, Integer> ingredientsCount = parser.idsAndCountFromInput(inputLine, keywordsToIds);
            List<Ingredient> ingredients = MenuUtils.ingredientsFromIdAndCount(ingredientsCount, articles);
            if (ingredients.isEmpty()) {
                inputLine = input.ask("Entschuldigung, ich habe dich nicht verstanden. Wähle aus folgenden Zutaten: "
                        + MenuUtils.focusOnNames(articles));
            } else {
                for (Ingredient ing : ingredients) builder.add(ing);
                Pizza pizza = builder.build();

                var comparator = Comparator.comparing(Ingredient::getName);

                List<Ingredient> ingrSorted = ingredients.stream().sorted(comparator).collect(Collectors.toList());

                // expected: "In Ordnung. Deine Pizza mit [Basilikum als Topping, Champignons als Topping, Italian-style-Teig, Olivenöl als Sauce, Seitan-Chicken] kostet 2.80 Euro. Willst du die Bestellung abschliessen?";
                // actual:   "In Ordnung. Deine Pizza mit [Basilikum, Champignons, Italian-style, Olivenöl, Seitan-Chicken] kostet 2.8 Euro. Willst du die Bestellung abschliessen?
                String toAsk = "In Ordnung. Deine Pizza mit " + ingrSorted + " kostet " + formatterBigDecimal(pizza.calculatePrice()) + " Euro. Willst du die Bestellung abschliessen?";

                inputLine = input.ask(toAsk);

            }
        }

        return inputLine;
    }

    private BigDecimal formatterBigDecimal(BigDecimal bigDecimal) {

        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal;

    }



}

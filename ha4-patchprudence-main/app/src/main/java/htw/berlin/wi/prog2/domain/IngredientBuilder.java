package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class IngredientBuilder {

    private String name;
    private BigDecimal price;
    private int calories;

    public IngredientBuilder setName(String name) {

        if (name != null) {
            this.name = name.trim();
        }

        return this;

    }

    public IngredientBuilder setPrice(String price) {

        if (price != null) {
            price = !price.contains(".") ? price : price.replaceAll("0*$", "").replaceAll("\\.$", "");
            this.price = new BigDecimal(price);
        }

        return this;

    }

    public IngredientBuilder setCals(int calories) {
        this.calories = calories;
        return this;
    }

    public Ingredient build(Ingredient.Category cat) {

        Ingredient ingredient = null;

        if (cat == Ingredient.Category.SAUCE) {

            ingredient = new Sauce(name, price, calories);

        } else if (cat == Ingredient.Category.TOPPING) {

            ingredient = new Topping(name, price, calories);

        } else if (cat == Ingredient.Category.BASE) {

            ingredient = new Base(name, price, calories);

        } else if (cat == Ingredient.Category.PROTEIN) {

            ingredient = new Protein(name, price, calories);

        }

        return ingredient;

    }
}

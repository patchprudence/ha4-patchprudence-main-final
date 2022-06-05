package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class Sauce extends AbstractIngredient {

    private final Category category;

    public Sauce(String name, BigDecimal price, int calories) {
        super(name, price, calories);
        this.category = Category.SAUCE;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return this.getName() + " als Sauce";
    }

}

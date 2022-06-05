package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class Topping extends AbstractIngredient {

    private final Category category;

    public Topping(String name, BigDecimal price, int calories) {
        super(name, price, calories);
        this.category = Category.TOPPING;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return this.getName() + " als Topping";
    }
}

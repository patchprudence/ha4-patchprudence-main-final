package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class Base extends AbstractIngredient {

    private final Category category;

    public Base(String name, BigDecimal price, int calories) {
        super(name, price, calories);
        this.category = Category.BASE;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return this.getName() + "-Teig";
    }

}

package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public class Protein extends AbstractIngredient {

    private final Category category;

    public Protein(String name, BigDecimal price, int calories) {
        super(name, price, calories);
        this.category = Category.PROTEIN;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

}

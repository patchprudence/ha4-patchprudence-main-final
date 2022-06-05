package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

public abstract class AbstractIngredient implements Ingredient {

    private final String name;
    private final BigDecimal price;
    private final int calories;

    public AbstractIngredient(String name, BigDecimal price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() { return this.getName(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractIngredient that = (AbstractIngredient) o;

        if (calories != that.calories) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + calories;
        return result;
    }
}

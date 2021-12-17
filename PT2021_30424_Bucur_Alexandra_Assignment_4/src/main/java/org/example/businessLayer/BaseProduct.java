package org.example.businessLayer;

import java.io.Serializable;
import java.util.Objects;

public class BaseProduct extends MenuItem implements Serializable {

    /**
     * construcor from the superclass
     *
     * @param title    string
     * @param rating   double
     * @param calories int
     * @param protein  int
     * @param fat      int
     * @param sodium   int
     * @param price    int
     */
    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    /**
     * basic construcor
     */
    public BaseProduct() {
    }

    /**
     * computes price of an item => returns the field price
     *
     * @return int
     */
    @Override
    public int computePrice() {
        return super.computePrice();
    }

    /**
     * only the name is used for hashing due to considering different objects as having different names
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getTitle());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BaseProduct)) return false;
        BaseProduct menuItem = (BaseProduct) obj;
        return Objects.equals(this.getTitle(), menuItem.getTitle());
    }
}

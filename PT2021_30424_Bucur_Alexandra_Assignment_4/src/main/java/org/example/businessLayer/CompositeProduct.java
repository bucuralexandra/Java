package org.example.businessLayer;

import java.util.ArrayList;

/**
 * @author Alexandra
 * @since 12/05/2021
 */
public class CompositeProduct extends MenuItem {

    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    /**
     * construcor
     *
     * @param title title of the newly created item
     * @param items being a composite product ->
     *              it will contain some BaseProducts/more composite products
     *              <p>
     *              the fields are calculated as sums of the component products
     */
    public CompositeProduct(String title, ArrayList<MenuItem> items) {
        super(title);
        this.menuItems = items;
        double rating = 0;
        int protein = 0;
        int calories = 0;
        int fat = 0;
        int sodium = 0;
        int price = 0;
        for (MenuItem item : this.menuItems) {
            rating += item.getRating();
            protein += item.getProtein();
            calories += item.getCalories();
            fat += item.getFat();
            sodium += item.getSodium();
            price += item.computePrice();
        }
        rating /= menuItems.size();
        this.setFat(fat);
        this.setPrice(price);
        this.setCalories(calories);
        this.setProtein(protein);
        this.setRating(rating);
        this.setSodium(sodium);
    }

    /**
     * method that will compute the price as the sum of all items that it contains
     * @return
     */
    @Override
    public int computePrice() {
        int price = 0;
        for (MenuItem item : menuItems) {
            price += item.computePrice();
        }
        return price;
    }

    /**
     * method that will show nicely all products from a composite product
     *
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getTitle() + "\n");
        for (MenuItem menuItem : this.menuItems) {
            stringBuilder.append(menuItem.toString() + "\n");
        }
        return stringBuilder.toString();
    }
}

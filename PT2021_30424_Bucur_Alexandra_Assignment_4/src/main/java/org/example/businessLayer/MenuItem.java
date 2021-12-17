package org.example.businessLayer;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Alexandra
 * @since 12/02/2021
 */
public abstract class MenuItem implements Serializable {

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;


    /**
     * constructor used later for the composite products
     *
     * @param title
     */
    public MenuItem(String title) {
        this.title = title;
    }

    /**
     * basic constructor
     */
    public MenuItem() {
    }

    /**
     * constructor later used by the base product, when created
     *
     * @param title    string
     * @param rating   double
     * @param calories int
     * @param protein  int
     * @param fat      int
     * @param sodium   int
     * @param price    int
     */
    public MenuItem(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    /**
     * getters and setters
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * getters and setters
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getters and setters
     */
    public double getRating() {
        return rating;
    }

    /**
     * getters and setters
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * getters and setters
     */
    public int getProtein() {
        return protein;
    }

    /**
     * getters and setters
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }

    /**
     * getters and setters
     */
    public int getFat() {
        return fat;
    }

    /**
     * getters and setters
     */
    public void setFat(int fat) {
        this.fat = fat;
    }

    /**
     * getters and setters
     */

    public int getSodium() {
        return sodium;
    }

    /**
     * getters and setters
     */
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    /**
     * getters and setters
     */
    public int computePrice() {
        return price;
    }

    /**
     * getters and setters
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * getters and setters
     */
    public int getCalories() {
        return calories;
    }

    /**
     * getters and setters
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * getters and setters
     */
    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem item = (MenuItem) o;
        return Objects.equals(title, item.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "title= " + title +
                "price=" + price;
    }
}

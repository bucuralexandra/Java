package org.example.model;

/**
 * @author Alexandra
 * @class Product
 * this class is a simple class that deals with the operations of a product. It only has getters and setters
 * that deals with the data from the table that has the same name
 * @since 19/04/2021
 */
public class Product {

    private int id;
    private String name;
    private int price;
    private int nrProductsInStock;

    /**
     * @param id          id of the product
     * @param name        name of the product
     * @param price       price of the product, int
     * @param stockPieces quantity that exists in the database
     */

    public Product(int id, String name, int price, int stockPieces) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.nrProductsInStock = stockPieces;
    }

    /**
     * Basic constructor
     */
    public Product() {

    }

    /**
     * @param name        name of the product
     * @param price       price of the product, int
     * @param stockPieces quantity that exists in the database
     */
    public Product(String name, int price, int stockPieces) {
        this.name = name;
        this.price = price;
        this.nrProductsInStock = stockPieces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNrProductsInStock() {
        return nrProductsInStock;
    }

    public void setNrProductsInStock(int nrProductsInStock) {
        this.nrProductsInStock = nrProductsInStock;
    }

    public void decreaseProductsInStock(int nrProducts) {
        this.nrProductsInStock = this.nrProductsInStock - nrProducts;
    }

    /**
     * @return the "output " form of the object
     */
    @Override
    public String toString() {
        return "Product: " + "name=" + this.name + ", price= " + this.price;
    }

    public String toStringAllDetails() {
        return "Product: " + "\n" +
                "name=" + this.name + "\n" +
                "price= " + this.price;
    }
}

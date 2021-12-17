package org.example.model;

/**
 * @author Alexandra
 * @class Order
 * Simple class that deals with retrieving/updating information about an order from the database
 * is sets/gets some fields
 * @since 19/04/2021
 */
public class Order {

    private int id;
    private int idClient;
    private int idProduct;
    private int quantityOfProduct;

    /**
     * @param id        id of an order
     * @param idClient  id of the client that makes the order
     * @param idProduct id of the product that has been ordered
     * @param quantity  how many products of the same type does the client want
     * @Constructor
     */
    public Order(int id, int idClient, int idProduct, int quantity) {
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantityOfProduct = quantity;
    }

    /**
     * @param idClient  id of the client that makes the order
     * @param idProduct id of the product that has been ordered
     * @param quantity  how many products of the same type does the client want
     * @Constructor
     */
    public Order(int idClient, int idProduct, int quantity) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantityOfProduct = quantity;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(int quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }
}

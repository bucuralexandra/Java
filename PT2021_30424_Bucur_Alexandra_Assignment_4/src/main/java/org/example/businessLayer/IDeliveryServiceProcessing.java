package org.example.businessLayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public interface IDeliveryServiceProcessing {

    /**
     * @post  itemss!= null;
     */
    HashSet<MenuItem> importProducts();

    /**
     * method that manages the products
     *
     * @param menuItem menu item that will be changed
     * @param name     the new name
     * @param rating   the new rating
     * @param cal      the new calories field
     * @param protein  new amount of protein
     * @param sodium   new amount of sodium
     * @param fat      new amount of fat
     * @param price    new amount price
     * @pre menuItem != null
     * @post menuItem != null
     * @pre wellFormed()
     */
    void modifyProduct(MenuItem menuItem, String name, float rating, int cal, int protein, int sodium, int fat, int price);


    /**
     * method that will delete an item from the "database"
     *
     * @param menuItem the item to be deleted
     * @pre menuItem !=null the item should not be null
     * @post !items.contains(menuItem);
     */
    void deleteProduct(MenuItem menuItem);

    /**
     * @param menuItem the item to be added to the menu
     * @pre menuItem != null
     * @post items.contains(item)
     */

    void addProduct(MenuItem menuItem);

    /**
     * @param calendar the date needed for the report
     * @return a string containing all the orders/products that
     * satisfy the conditions
     * @pre calendar != null
     */
    String generateReports(Calendar calendar);

    /**
     * methods that creates a new order
     *
     * @param order order to be created, with the id already set
     * @param items items to be added to the delivery service
     * @param user  the user that created the order
     * @pre order != null
     * @pre items != null
     * @pre user != null
     * @post order != null
     * @post orderArrayListHashtable.contains(items)
     */
    void createOrder(Order order, ArrayList<MenuItem> items, User user);

    /**
     * @param order order to have its price computed
     * @return price
     * @pre order != null
     * @post price > 0
     */
    int computeOrderPrice(Order order);

    /**
     * @param order        order to be showed
     * @param totalPrice   total price of the order
     * @param itemsOrdered items that were ordered
     * @pre order != null
     * @pre totalPrice >0
     */
    void generateBill(Order order, int totalPrice, ArrayList<MenuItem> itemsOrdered);

    /**
     * @param minH the min hour
     * @param maxH the max hour
     *             => interval between those 2
     * @return a string containing all the orders that satisfy the conditions
     * @pre minH > 0 and  maxH > 0  and minH < maxH
     */
    String generateReportsTimeInterval(int minH, int maxH);

    /**
     * @param nrTimes the amount of times a product has been ordered
     * @return a string containing all the orders that satisfy the conditions
     * @pre nrTimes >=0
     */
    String generateProductNrTimes(int nrTimes);

    /**
     * @param nrTimes     the amount of times a product has been ordered
     * @param totalAmount the amount of an order in order to client to be considered good for
     *                    the report
     * @return a string containing all the clients that satisfy the conditions
     * @pre nrTimes >=0 and totalAmount >=0
     */
    String generateClientsNrTimes(int nrTimes, int totalAmount);

    /**
     * @param name    to be searched
     * @param rating  to be searched
     * @param cal     to be searched
     * @param protein to be searched
     * @param fat     to be searched
     * @param sodium  to be searched
     * @param price   to be searched
     * @return an array of items that satisfy the conditions
     * @pre items != null
     */
    ArrayList<MenuItem> findItems(String name, String rating, String cal, String protein, String fat, String sodium, String price);
}

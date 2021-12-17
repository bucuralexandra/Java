package org.example.businessLayer;

import org.example.dataLayer.FileReaderCSV;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alexandra
 * @invariant wellFormed()
 * @since 22/05/2021
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private Hashtable<Order, ArrayList<MenuItem>> orderArrayListHashtable = new Hashtable<>();
    private ArrayList<MenuItem> items = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    /**
     * method that will search all the objects that satify all the
     * conditions that are valid
     */
    public ArrayList<MenuItem> findItems(String name, String rating, String cal, String protein, String fat, String sodium, String price) {
        ArrayList<MenuItem> aux = items;
        if (!name.equals("Name") && !name.equals("")) {
            aux = (ArrayList<MenuItem>) items.stream().filter(itm -> itm.getTitle().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
        }
        if (!rating.equals("Rating") && !rating.equals("")) {
            aux = (ArrayList<MenuItem>) aux.stream().filter(itm -> String.valueOf(itm.getRating()).equals(rating)).collect(Collectors.toList());
        }
        if (!cal.equals("Calories") && !cal.equals("")) {
            aux = (ArrayList<MenuItem>) aux.stream().filter(itm -> String.valueOf(itm.getCalories()).equals(cal)).collect(Collectors.toList());
        }
        if (!protein.equals("Protein") && !protein.equals("")) {
            aux = (ArrayList<MenuItem>) aux.stream().filter(itm -> String.valueOf(itm.getProtein()).equals(protein)).collect(Collectors.toList());
        }
        if (!fat.equals("Fat") && !fat.equals("")) {
            aux = (ArrayList<MenuItem>) aux.stream().filter(itm -> String.valueOf(itm.getFat()).equals(fat)).collect(Collectors.toList());
        }
        if (!sodium.equals("Sodium") && !sodium.equals("")) {
            aux = (ArrayList<MenuItem>) aux.stream().filter(itm -> String.valueOf(itm.getSodium()).equals(sodium)).collect(Collectors.toList());
        }
        if (!price.equals("Price") && !price.equals("")) {
            aux = (ArrayList<MenuItem>) aux.stream().filter(itm -> String.valueOf(itm.getPrice()).equals(price)).collect(Collectors.toList());
        }
        return aux;
    }


    /**
     * method that will import all products from the file of products
     */
    @Override
    public HashSet<MenuItem> importProducts() {
        FileReaderCSV fileReader = new FileReaderCSV();
        HashSet<MenuItem> itemss = fileReader.processInputFile();
        assert itemss!= null;
        return itemss;
    }

    /**
     * @param menuItem menu item to be changed
     * @param name     the new name
     * @param rating   the new rating
     * @param cal      new calories
     * @param protein  new protein (int)
     * @param sodium   new sodium
     * @param fat      new fat
     * @param price    new price
     *                 <p>
     *                 method that will modify a product
     */
    @Override
    public void modifyProduct(MenuItem menuItem, String name, float rating, int cal, int protein, int sodium, int fat, int price) {

        assert wellFormed();
        assert menuItem != null;

        int menuIndex = items.indexOf(menuItem);
        MenuItem menuItemAux = items.get(menuIndex);
        menuItemAux.setTitle(name);
        menuItemAux.setPrice(price);
        menuItemAux.setRating(rating);
        menuItemAux.setSodium(sodium);
        menuItemAux.setCalories(cal);
        menuItemAux.setProtein(protein);
        menuItemAux.setFat(fat);
        items.set(menuIndex, menuItemAux);
        assert menuItem != null;
    }

    /**
     * method that will delete a product from the list
     *
     * @param menuItem item to be removed
     */
    @Override
    public void deleteProduct(MenuItem menuItem) {
         assert menuItem !=null;
        items.remove(menuItem);
        assert  !items.contains(menuItem);
    }

    /**
     * method that will add a new product into the list of products
     *
     * @param menuItem item to be removed
     */
    @Override
    public void addProduct(MenuItem menuItem) {
        assert wellFormed();
         assert menuItem != null;
        boolean ok = true;
        for (MenuItem item : items) {
            if (item.equals(menuItem)) {
                ok = false;
            }
        }
        if (ok) {
            items.add(menuItem);
        }
        assert  items.contains(menuItem);
    }

    /**
     * @param calendar the date needed for the report
     * @return
     */
    @Override
    public String generateReports(Calendar calendar) {
        assert calendar != null;
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Order> orders = new ArrayList<>(orderArrayListHashtable.keySet());
        ArrayList<Order> aux = new ArrayList<>();
        aux = (ArrayList<Order>) orders.stream()
                .filter(itm -> itm.getOrderDate().get(Calendar.YEAR) == year
                        && itm.getOrderDate().get(Calendar.MONTH) == month - 1
                        && itm.getOrderDate().get(Calendar.DAY_OF_MONTH) == day)
                .collect(Collectors.toList());
        ArrayList<MenuItem> items = new ArrayList<>();
        for (Order order : aux) {
            items.addAll(getOrderArrayListHashtable().get(order));
        }
        Map<String, Integer> map =
                items.stream()
                        .collect(Collectors.groupingBy(MenuItem::getTitle,
                                Collectors.summingInt(e -> 1)));
        ArrayList<String> itemsNames = new ArrayList<>(map.keySet());
        itemsNames.stream().forEach(itm -> stringBuilder.append(itm + map.get(itm) + "\n"));

        return stringBuilder.toString();
    }

    public Hashtable<Order, ArrayList<MenuItem>> getOrderArrayListHashtable() {
        return orderArrayListHashtable;
    }

    @Override
    public void createOrder(Order order, ArrayList<MenuItem> items, User client) {
        assert wellFormed();
        order.setClientID(client.getId());
        Calendar date = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        order.setOrderDate(date);
        orderArrayListHashtable.put(order, items);
        StringBuilder orderString = new StringBuilder();
        orderString.append("Order: " + order.getOrderID() + "\n");
        orderString.append("Date:" + simpleDateFormat.format(order.getOrderDate().getTime()) + "\n");
        orderString.append("Client: " + order.getClientID() + "\n");
        orderString.append(client.getUsername() + "\n");
        orderString.append("Items: \n");
        for (MenuItem i : items) {
            orderString.append(i.toString() + "\n");
        }
        orderString.append("\n");
        orderString.append("Total price: " + computeOrderPrice(order) + "\n");
        assert wellFormed();
        setChanged();
        notifyObservers(orderString.toString());
    }

    /**
     * computes the price of the order
     *
     * @param order order to have its price computed
     * @return
     */
    @Override
    public int computeOrderPrice(Order order) {
          assert order != null;

        int price = 0;
        ArrayList<MenuItem> orderItems = orderArrayListHashtable.get(order);
        for (MenuItem item : orderItems) {
            price += item.computePrice();
        }
        assert price >0;
        return price;
    }

    /**
     * generated the bill
     *
     * @param order        order to be showed
     * @param totalPrice   total price of the order
     * @param itemsOrdered items that were ordered
     */
    @Override
    public void generateBill(Order order, int totalPrice, ArrayList<MenuItem> itemsOrdered) {
      assert order != null;
           assert  totalPrice >0;
        try {
            StringBuilder fileName = new StringBuilder();
            fileName.append("bill");
            fileName.append(order.getOrderID());
            fileName.append(".txt");
            PrintWriter writer = new PrintWriter(fileName.toString(), "UTF-8");
            writer.println(order.toString());
            for (MenuItem item : itemsOrdered) {
                writer.println(item.toString());
            }
            writer.println("Total cost : " + totalPrice);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("File not found for the bill");
        }
    }

    public ArrayList<MenuItem> getItems() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        for (MenuItem item : items) {
            menuItems.add(item);
        }
        return menuItems;
    }

    public void setItems(HashSet<MenuItem> itemss) {
        ArrayList list = new ArrayList();
        for (MenuItem item : itemss) {
            list.add(item);
        }
        this.items = list;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * @param minH the min hour
     * @param maxH the max hour
     *             => interval between those 2
     * @return
     */
    @Override
    public String generateReportsTimeInterval(int minH, int maxH) {

        assert minH > 0;
        assert maxH > 0;
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Order> orders = new ArrayList<>(orderArrayListHashtable.keySet());
        ArrayList<Order> aux;
        aux = (ArrayList<Order>) orders.stream().filter(item -> item.getOrderDate().get(Calendar.HOUR_OF_DAY) < maxH
                && item.getOrderDate().get(Calendar.HOUR_OF_DAY) >= minH  ).collect(Collectors.toList());
        aux.stream().forEach(i -> stringBuilder.append(i.toString() + "\n"));
        return stringBuilder.toString();
    }

    /**
     * @param nrTimes the amount of times a product has been ordered
     * @return string
     */
    @Override
    public String generateProductNrTimes(int nrTimes) {
        assert  nrTimes >=0;
        //the products ordered more than a specified number of times so far.
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<ArrayList<MenuItem>> menuItems = new ArrayList<>(orderArrayListHashtable.values());
        ArrayList<MenuItem> items = new ArrayList<>();
        menuItems.stream().forEach(items::addAll);
        Map<String, Integer> map =
                items.stream()
                        .collect(Collectors.groupingBy(MenuItem::getTitle,
                                Collectors.summingInt(e -> 1)));
        ArrayList<String> itemsNames = new ArrayList<>(map.keySet());
        itemsNames = (ArrayList<String>) itemsNames.stream().filter(itm -> map.get(itm) > nrTimes).collect(Collectors.toList());
        itemsNames.stream().forEach(itm -> stringBuilder.append(itm + "\n"));
        return stringBuilder.toString();
    }

    /**
     * @param nrTimes     the amount of times a product has been ordered
     * @param totalAmount the amount of an order in order to client to be considered good for
     *                    the report
     * @return
     */
    public String generateClientsNrTimes(int nrTimes, int totalAmount) {
        assert  items != null;
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Order> orders = new ArrayList<>(orderArrayListHashtable.keySet());
        ArrayList<Order> aux;
        aux = (ArrayList<Order>) orders.stream().filter(itm -> computeOrderPrice(itm) > totalAmount).collect(Collectors.toList());
        Map<UUID, Integer> map =
                aux.stream()
                        .collect(Collectors.groupingBy(Order::getClientID,
                                Collectors.summingInt(e -> 1)));
        ArrayList<UUID> ids = new ArrayList<>(map.keySet());
        ids = (ArrayList<UUID>) ids.stream().filter(itm -> map.get(itm) > nrTimes).collect(Collectors.toList());
        Set<UUID> setOne = new HashSet<>(ids);
        List<User> clients = users.stream()
                .filter(e -> setOne.contains(e.getId()))
                .collect(Collectors.toList());
        clients.stream().forEach(i -> stringBuilder.append(i.toStringReports() + "\n"));
        return stringBuilder.toString();
    }

    public boolean wellFormed() {
        return (this.users != null && this.items != null);
    }

}

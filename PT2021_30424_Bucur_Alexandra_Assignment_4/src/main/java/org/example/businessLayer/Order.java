package org.example.businessLayer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class Order implements Serializable {

    private UUID orderID;
    private UUID clientID;
    private Calendar orderDate;


    /**
     * basic constructor where the id is set
     */
    public Order(){
     this.orderID = UUID.randomUUID();
    }
    public Order(UUID orderID, UUID clientID, Calendar orderDate) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
        this.orderID = UUID.randomUUID();
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void setOrderID(UUID orderID) {
        this.orderID = orderID;
    }

    public UUID getClientID() {
        return clientID;
    }


    /**
     * hash code that takes all the fields of the order
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(orderID,clientID,orderDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderID == order.orderID && clientID == order.clientID && Objects.equals(orderDate, order.orderDate);
    }

    public void setClientID(UUID clientID) {
        this.clientID = clientID;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "OrderID " + orderID + "\n"+
                "clientID=" + clientID + "\n"+
                "orderDate=" + simpleDateFormat.format(orderDate.getTime())
                + "\n";
    }
}

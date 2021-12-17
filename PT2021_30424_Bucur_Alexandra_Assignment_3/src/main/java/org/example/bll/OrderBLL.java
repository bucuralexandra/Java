package org.example.bll;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import org.example.DAO.OrderDao;
import org.example.model.Order;

import java.util.List;

/**
 * @author Alexandra
 * @class OrderBLL
 * @since 19/04/2021
 */
public class OrderBLL {

    private OrderDao orderDao;

    /**
     * Constructor that initializes the object
     */
    public OrderBLL() {
        orderDao = new OrderDao();
    }

    /**
     * @param order order to be inserted into the table
     * @method method that inserts through the help of the orderDao an order into the database
     */
    public void insert(Order order) {
        orderDao.insert(order);
    }

    /**
     * @return a list of all orders from the database
     * @method that returns a list of all the orders
     */
    public List<Order> showAll() {
        return orderDao.findAll();
    }

    /**
     * @return a list with all the column names that have to be shown on the UI.
     */
    public ObservableList<TableColumn<Order, ?>> getCols() {
        return orderDao.getColumns(new Order());
    }

    /**
     * @param id id of the order that has to be deleted
     * @return true/false that represents if the order can be deleted or not
     */
    public boolean delete(int id) {
        Order st = orderDao.findById(id);
        if (st == null) {
            return false;
        }
        orderDao.delete(st);
        return true;
    }
}

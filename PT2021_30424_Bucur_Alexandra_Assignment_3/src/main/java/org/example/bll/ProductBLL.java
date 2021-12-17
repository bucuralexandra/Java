package org.example.bll;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import org.example.DAO.OrderDao;
import org.example.DAO.ProductDao;
import org.example.bll.validators.NumberValidator;
import org.example.bll.validators.Validator;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandra
 * @class ProductBLL
 * class that will deal with validating all the product input and CRUD operation in the database
 * @since 17/04/2021
 */
public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDao productDao;

    /**
     * constructor that initializes the data and the validator fields
     */
    public ProductBLL() {
        validators = new ArrayList<>();
        validators.add(new NumberValidator());
        productDao = new ProductDao();
    }

    /**
     * @param id id that will be searched into the database
     * @return a product object/ null if the no products are found
     */
    public Product findProductById(int id) {
        Product st = productDao.findById(id);
        if (st == null) {
            return null;
        }
        return st;
    }

    /**
     * @param product product object that has new information and will be updated into the database
     * @return true/false depending if the update was possible
     */
    public boolean update(Product product) {
        for (Validator validator : this.validators) {
            if (validator.validate(product) == -1) {
                return false;
            }
        }
        productDao.update(product);
        return true;
    }

    /**
     * @param product product object that has new information and will be inserted into the database
     * @return true/false depending if the insert was possible
     */
    public boolean insert(Product product) {
        for (Validator validator : this.validators) {
            if (validator.validate(product) == -1) {
                return false;
            }
        }
        productDao.insert(product);
        return true;
    }

    /**
     * @return list<Product>
     * @method that returns all the products from the database
     */
    public List<Product> showAll() {
        return productDao.findAll();
    }

    /**
     * @return a list of table columns for the product table in the UI
     */
    public ObservableList<TableColumn<Product, ?>> getCols() {
        return productDao.getColumns(new Product());
    }

    /**
     * @param id if of the product that will be deleted
     * @return true/false depending whether the delete was possible or not
     * @method that will find a product with the given id and delete it from the database
     * the method will also delete all the orders that included the product
     */
    public boolean delete(int id) {
        Product st = productDao.findById(id);
        OrderDao orderDao = new OrderDao();
        orderDao.deleteSpecificOrder(id, "Product");
        if (st == null) {
            return false;
        }
        productDao.delete(st);
        return true;
    }
}

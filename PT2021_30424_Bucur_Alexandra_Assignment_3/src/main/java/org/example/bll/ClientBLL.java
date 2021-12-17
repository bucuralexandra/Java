package org.example.bll;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import org.example.DAO.ClientDao;
import org.example.DAO.OrderDao;
import org.example.bll.validators.EmailValidator;
import org.example.bll.validators.PhoneNumberValidator;
import org.example.bll.validators.Validator;
import org.example.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandra
 * @class ClientBLL
 * class that will deal with validating all the client input and CRUD operation in the database
 * @since 17/04/2021
 */

public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDao clientDao;

    /**
     * constructor that initializes the object and all its validators
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new PhoneNumberValidator());
        clientDao = new ClientDao();
    }

    /**
     * @param id id that will be searched into the database
     * @return a client object/ null if the no clients is found
     */
    public Client findClientById(int id) {
        Client st = clientDao.findById(id);
        if (st == null) {
            return null;
        }
        return st;
    }

    /**
     * @param client client object that has new information and will be updated into the database
     * @return true/false depending if the update was possible
     */
    public boolean update(Client client) {
        for (Validator validator : this.validators) {
            if (validator.validate(client) == -1) {
                return false;
            }
        }
        clientDao.update(client);
        return true;
    }

    /**
     * @param client client object that has new information and will be inserted into the database
     * @return true/false depending if the insert was possible
     */
    public boolean insert(Client client) {
        for (Validator validator : this.validators) {
            if (validator.validate(client) == -1) {
                return false;
            }
        }
        clientDao.insert(client);
        return true;
    }

    /**
     * @return list<Client>
     * @method that returns all the clients from the database
     */
    public List<Client> showAll() {
        return clientDao.findAll();
    }

    /**
     * @return a list of table columns for the client table
     */
    public ObservableList<TableColumn<Client, ?>> getCols() {
        return clientDao.getColumns(new Client());
    }

    /**
     * @param id if of the client that will be deleted
     * @return true/false depending whether the delete was possible or not
     * @method that will find a client with the given id and delete it from the database
     * the method will also delete all the orders that included the client
     */
    public boolean delete(int id) {

        OrderDao orderDao = new OrderDao();
        orderDao.deleteSpecificOrder(id, "Client");
        Client st = clientDao.findById(id);
        if (st == null) {
            return false;
        }
        clientDao.delete(st);
        return true;
    }
}
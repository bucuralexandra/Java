package org.example.DAO;

import org.example.connection.ConnectionFactory;
import org.example.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * @author Alexandra
 * @class OrderDao
 * <p>
 * This class contains some specific methods for the order table, besides the ones
 * that we have from the Dao class
 * @see Dao
 * @since 19/04/2021
 */
public class OrderDao extends Dao<Order> {

    /**
     * @param id   id of the client or product that has to be deleted
     * @param mode client/ product that was deleted
     * @method that will create a query which will delete all the rows(all orders) that have
     * either a specified idClient or idProduct
     * This method is used for deleting an order that had a client/product already deleted
     */
    public void deleteSpecificOrder(int id, String mode) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            StringBuilder query = new StringBuilder();
            if (mode.equals("Client")) {
                query.append("DELETE FROM sys.Order");
                query.append(" WHERE idClient = " + id);

            } else {
                query.append("DELETE FROM sys.Order");
                query.append(" WHERE idProduct = " + id);
            }
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:deleteOrder " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}

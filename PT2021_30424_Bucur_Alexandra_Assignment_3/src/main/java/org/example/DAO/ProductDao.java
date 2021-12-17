package org.example.DAO;

import org.example.connection.ConnectionFactory;
import org.example.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * @author Alexandra
 * @class ProductDao
 * <p>
 * This class contains some specific methods for the product table, besides the ones
 * that we have from the Dao class
 * <p>
 * In this class can be added all sorts of queries that are specific for the product table,
 * such as deleting products with a specific name/price
 * @see Dao
 * @since 19/04/2021
 */
public class ProductDao extends Dao<Product> {

    /**
     * @param name name of the client or product that has to be deleted
     * @method that will create a query which will delete all the rows(all products) that have
     * the specified name
     */
    public void deleteSpecificOrderProduct(String name) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM sys.Product");
            query.append(" WHERE name = " + "\"" + name + "\"");
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:deleteProductName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}

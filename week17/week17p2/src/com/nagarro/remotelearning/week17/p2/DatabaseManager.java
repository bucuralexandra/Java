package com.nagarro.remotelearning.week17.p2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());
    private static final String DBURL = "jdbc:mysql://localhost:3306/week17?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static final DatabaseManager singleInstance = new DatabaseManager();
    private static final String WARNING_MESSAGE = "An error occurred while trying to close the ";

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, WARNING_MESSAGE + "statement");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, WARNING_MESSAGE + "connection");
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, WARNING_MESSAGE + "statement");
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, WARNING_MESSAGE + "ResultSet");
            }
        }
    }
}

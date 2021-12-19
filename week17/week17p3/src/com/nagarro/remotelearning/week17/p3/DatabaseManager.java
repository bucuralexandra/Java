package com.nagarro.remotelearning.week17.p3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DBURL = "jdbc:mysql://localhost:3306/week17?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DBURL, USER, PASS);
        System.err.println("The connection is successfully obtained");
        return connection;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

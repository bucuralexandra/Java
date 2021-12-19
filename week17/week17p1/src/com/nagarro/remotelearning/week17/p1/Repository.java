package com.nagarro.remotelearning.week17.p1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    public List<Client> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM client";
        try {
            connection = DatabaseManager.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<Client> clients;
            clients = createObjects(resultSet);
            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(resultSet);
            DatabaseManager.close(statement);
            DatabaseManager.close(connection);
        }
        return null;
    }

    public Integer findByUsername(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM week17.client WHERE username =?";
        try {
            connection = DatabaseManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            List<Client> list;
            list = createObjects(resultSet);
            if (list == null || list.isEmpty()) {
                return -1;
            } else {
                return list.get(0).getBalance();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(resultSet);
            DatabaseManager.close(statement);
            DatabaseManager.close(connection);
        }
        return null;
    }

    public List<Client> showRichClients(int balance) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM week17.client WHERE balance >=?";
        try {
            connection = DatabaseManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, balance);
            resultSet = statement.executeQuery();
            List<Client> list;
            list = createObjects(resultSet);
            if (list == null || list.isEmpty()) return null;
            else return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(resultSet);
            DatabaseManager.close(statement);
            DatabaseManager.close(connection);
        }
        return null;
    }

    private List<Client> createObjects(ResultSet resultSet) {
        ArrayList<Client> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idClient = resultSet.getInt(1);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                String username = resultSet.getString(2);
                int balance = resultSet.getInt(5);
                Client instance = new Client(idClient, firstName, lastName, username, balance);
                list.add(instance);
            }
        } catch (SecurityException | IllegalArgumentException | SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}

package com.nagarro.remotelearning.week17.p2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    public Client findByUsername(String username) {
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
                return (Client) list;
            } else {
                return list.get(0);
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

    private List<Client> createObjects(ResultSet resultSet) {
        ArrayList<Client> list = new ArrayList<Client>();
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

    public void performTransaction(Client client, Transaction transaction) {

        Connection connection = null;
        PreparedStatement statement = null;

        StringBuilder query = new StringBuilder();
        query.append("UPDATE week17.client SET ");
        query.append("username = " + "\"" + client.getUsername() + "\"" + ",");
        query.append("first_name = " + "\"" + client.getFirstName() + "\"" + ",");
        query.append("last_name = " + "\"" + client.getLastName() + "\"" + ",");
        query.append("balance = " + "\"" + client.getBalance() + "\"");
        query.append(" WHERE client_id = " + client.getIdClient());

        StringBuilder queryInsert = new StringBuilder();
        queryInsert.append("INSERT INTO week17.transaction");
        queryInsert.append("(client_id,type,amount)");
        queryInsert.append("VALUES( ");
        queryInsert.append("\"" + transaction.getIdClient() + "\"" + ",");
        queryInsert.append("\"" + transaction.getType() + "\"" + ",");
        queryInsert.append("\"" + transaction.getAmount() + "\"");
        queryInsert.append(")");
        System.out.println(query);
        System.out.println(queryInsert);
        try {
            connection = DatabaseManager.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(query.toString());
            statement.executeUpdate();
            statement = connection.prepareStatement(queryInsert.toString());
            statement.execute(queryInsert.toString());
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(statement);
            DatabaseManager.close(connection);
        }
    }
}

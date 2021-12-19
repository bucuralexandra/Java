package com.nagarro.remotelearning.week17.p3;

import java.sql.SQLException;

public class Main {

    private static final String ERROR_MSG = "There was an error retrieving the metadata properties: ";

    public static void main(String[] args) {
        try {
            Metadata.printNameOfDatabase();
            Metadata.getColumnsMetadata(Metadata.getTablesMetadata());
        } catch (SQLException e) {
            System.err.println(ERROR_MSG + e.getMessage());
        } finally {
            DatabaseManager.close(Metadata.connection);
        }
    }
}

package com.nagarro.remotelearning.week17.p3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Metadata {

    public static final String TABLE_NAME = "TABLE_NAME";
    private static final String ERROR_MSG_META = "There was an error getting the metadata: ";
    private static final String ERROR_MSG_CON = "There was an error getting the connection: ";
    public static final String COLUMN_NAME = "COLUMN_NAME";
    public static final String TYPE_NAME = "TYPE_NAME";
    static Connection connection = null;
    static DatabaseMetaData metadata = null;

    static {
        try {
            connection = DatabaseManager.getConnection();
        } catch (SQLException e) {
            System.err.println(ERROR_MSG_CON + e.getMessage());
        }
        try {
            metadata = connection.getMetaData();
        } catch (SQLException e) {
            System.err.println(ERROR_MSG_META + e.getMessage());
        }
    }

    public static void printNameOfDatabase() throws SQLException {
        System.out.println("Database name: " + connection.getCatalog());
        System.out.println("\n");
    }

    public static List<String> getTablesMetadata() throws SQLException {
        String table[] = {"TABLE"};
        ResultSet rs;
        List<String> tables = new ArrayList<>();
        rs = metadata.getTables(null, null, null, table);
        while (rs.next()) {
            tables.add(rs.getString(TABLE_NAME));
        }
        rs.close();
        return tables;
    }

    public static void getColumnsMetadata(List<String> tables) throws SQLException {
        ResultSet rs;
        for (String actualTable : tables) {
            rs = metadata.getColumns(null, null, actualTable, null);
            System.out.println(actualTable.toUpperCase());
            while (rs.next()) {
                System.out.println(rs.getString(COLUMN_NAME) + " " + rs.getString(TYPE_NAME));
            }
            rs.close();
            System.out.println("\n");
        }
    }
}

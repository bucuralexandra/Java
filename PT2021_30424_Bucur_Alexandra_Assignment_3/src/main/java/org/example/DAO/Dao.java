package org.example.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that deals with some queries related to the database
 */
public class Dao<T> {
    protected static final Logger LOGGER = Logger.getLogger(Dao.class.getName());
    private final Class<T> type;

    public Dao() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @param field field used for the query in the table
     * @return the final query
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM sys.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * @param id id to be searched
     * @return the object that is found/null is no such object exists
     * @method this method searches through the database for an object based on the id
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> list;
            list = createObjects(resultSet);
            if (list == null || list.isEmpty()) return null;
            else return list.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * @return a list with all the entries
     * @method this method takes all the entries from a table and returns them
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * @param t object to be inserted into the table
     * @method this method takes a generic object and tries to insert it into the designated table.
     * The method uses reflexion techniques
     */
    public void insert(T t) {
        Connection connection = null; PreparedStatement statement = null;
        Field[] fields; fields = t.getClass().getDeclaredFields();
        try {
            T value;
            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO sys." + type.getSimpleName() + "(");
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                query.append(fields[i].getName() + ",");
            }
            query.deleteCharAt(query.length() - 1);
            query.append(") ");
            query.append("VALUES( ");
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                value = (T) fields[i].get(t);
                query.append("\"" + value + "\"" + ",");
            }
            query.deleteCharAt(query.length() - 1);    query.append(")");
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query.toString());
                statement.execute(query.toString());
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
            } finally {
                ConnectionFactory.close(statement); ConnectionFactory.close(connection);
            }
        } catch (IllegalAccessException e) {
            System.out.println("Not a good insert");
        }
    }

     /**
      * This method will firstly create a new query that selects all the data from the table
      * and then will execute the query into the database
      * the createObjects() method will return all the objects into a list
      * @return a list with all the entries from a table
      */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT ").append(" * ").append(" FROM sys.");
        query.append(type.getSimpleName());
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            resultSet = statement.executeQuery();
            List<T> list;
            list = createObjects(resultSet);
            if (list == null || list.isEmpty()) return null;
            else return list;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

     /**
      * The method will firstly create the query and then will apply it into the table
      * The query is produced with the help of reflexion techniques, at first it will get the table field
      * (type.getSimpleName()) and then will get all the fields and values from the
      * fields from the parameter t
      * @param t the new values to be updated into the table
      */
    public void update(T t) {
        Connection connection = null; PreparedStatement statement = null;
        Field[] fields;   fields = t.getClass().getDeclaredFields();
        try {
            T value;
            StringBuilder query = new StringBuilder();
            query.append("UPDATE sys." + type.getSimpleName() + " SET ");
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                value = (T) fields[i].get(t); //value in object t in the field i
                query.append(fields[i].getName() + "=" + "\"" + value + "\"" + ",");
            }
            query.deleteCharAt(query.length() - 1);
            fields[0].setAccessible(true);
            value = (T) fields[0].get(t);
            query.append(" WHERE id = " + value);
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
            System.out.println(query);
        } catch (IllegalAccessException e) {
            System.out.println("Not a good update");
        }
    }

     /**
      * this method will create a list of columns that will be displayed onto the ui
      * it will create a list based on the fields that exist in the object i
      * @param t type of object required for extracting the fields
      * @return a list of columns
      */
    public ObservableList<TableColumn<T, ?>> getColumns(T t) {

        ObservableList<TableColumn<T, ?>> columnsList = FXCollections.observableArrayList();
        Field[] fields;fields = t.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {

            TableColumn currentColumn = new TableColumn();
            String fieldName = fields[i].getName();
            if (fieldName.equals("id")) {
                currentColumn.setCellValueFactory(new PropertyValueFactory<T, Integer>(fieldName));
            } else {
                currentColumn.setCellValueFactory(new PropertyValueFactory<T, String>(fieldName));
            }
            currentColumn.setText(fieldName);
            columnsList.add(currentColumn);
        }
        return columnsList;
    }

     /**
      * The method will firstly create the query and then will apply it into the table
      * The query is produced with the help of reflexion techniques, at first it will get the table field
      * (type.getSimpleName()) and then will get the id field and value from the parameter t
      * @param t object to be deleted
      */
    public void delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        Field[] fields;
        fields = t.getClass().getDeclaredFields();
        try {
            T value;
            StringBuilder query = new StringBuilder();
            query.append("DELETE FROM sys." + type.getSimpleName());
            fields[0].setAccessible(true);
            value = (T) fields[0].get(t);
            query.append(" WHERE id = " + value);
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
        } catch (IllegalAccessException e) {
            System.out.println("Not a good delete");
        }
    }
}

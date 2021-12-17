package org.example.model;

/**
 * @author Alexandra
 * @class Client
 * simple class that deals with storing/retrieving data of a client
 * @since 19/04/2020
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    /**
     * @param id          id of a client
     * @param name        name of the client
     * @param email       email of a client
     * @param phoneNumber phone number of a client
     */
    public Client(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param name        name of the client
     * @param email       email of a client
     * @param phoneNumber phone number of a client
     */
    public Client(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * basic constructor with no fields
     */
    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return string that contains the output form of the client class
     */
    @Override
    public String toString() {
        return "Client: " + "name=" + this.name + ", email=" + this.email;
    }

    /**
     * @return string that contains the output form of the client class
     */
    public String toStringAllDetails() {
        return "Client: " + "\n" +
                "name=" + this.name + "\n" +
                "email=" + this.email + "\n" +
                "phoneNumber= " + this.phoneNumber + "\n";
    }
}

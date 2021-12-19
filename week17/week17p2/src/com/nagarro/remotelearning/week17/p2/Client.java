package com.nagarro.remotelearning.week17.p2;


public class Client {


    private int idClient;
    private String firstName;
    private String lastName;
    private String username;
    private int balance;

    public Client(int idClient, String firstName, String lastName, String username, int balance) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public void changeBalance(int balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return "Client " +
                " firstName=" + firstName +
                "   lastName=" + lastName +
                "   username=" + username +
                "   balance=" + balance;

    }
}

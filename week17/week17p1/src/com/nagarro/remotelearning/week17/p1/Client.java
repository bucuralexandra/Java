package com.nagarro.remotelearning.week17.p1;

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

    @Override
    public String toString() {
        return "Client " +
                " firstName=" + firstName +
                "   lastName=" + lastName +
                "   username=" + username +
                "   balance=" + balance;

    }
}

package com.nagarro.remotelearning.week17.p1;

public class Transaction {

    private int idTransaction;
    private int idClient;
    private Type type;
    private int amount;

    public Transaction() {
    }

    public Transaction(int idTransaction, int idClient, Type type, int amount) {
        this.idTransaction = idTransaction;
        this.idClient = idClient;
        this.type = type;
        this.amount = amount;
    }

    public Transaction(int idClient, Type type, int amount) {
        this.idClient = idClient;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                ", idClient=" + idClient +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}

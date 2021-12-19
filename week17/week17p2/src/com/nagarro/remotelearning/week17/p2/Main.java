package com.nagarro.remotelearning.week17.p2;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Repository repository = new Repository();
        Client retrievedClient = repository.findByUsername("bucuralexandra");
        Transaction transaction = new Transaction(retrievedClient.getIdClient(), Type.OUT, -200);
        retrievedClient.changeBalance(transaction.getAmount());
        repository.performTransaction(retrievedClient,transaction);

        retrievedClient = repository.findByUsername("stefanmirauti");
        transaction = new Transaction(retrievedClient.getIdClient(), Type.IN, 1000);
        retrievedClient.changeBalance(transaction.getAmount());
        repository.performTransaction(retrievedClient,transaction);

    }
}

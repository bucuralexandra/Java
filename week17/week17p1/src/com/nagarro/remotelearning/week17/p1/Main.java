package com.nagarro.remotelearning.week17.p1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Integer BALANCE = 100000;

    public static void main(String[] args) {

        Repository repository = new Repository();
        List<Client> clients = repository.findAll();

        System.out.println("List of all clients");
        for (Client c : clients) {
            System.out.println(c.toString());
        }
        System.out.println("=============================================");
        System.out.println("Clients found by username");
        System.out.println(repository.findByUsername("bucuralexandra"));
        System.out.println(repository.findByUsername("nicoletapop"));
        System.out.println(repository.findByUsername("mm"));

        System.out.println("=============================================");
        System.out.println("List of clients with balance over 100.000");
        clients = repository.showRichClients(BALANCE);
        for (Client c : clients) {
            System.out.println(c.toString());
        }
    }
}

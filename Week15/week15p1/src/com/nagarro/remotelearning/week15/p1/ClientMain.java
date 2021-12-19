package com.nagarro.remotelearning.week15.p1;

import java.io.IOException;

public class ClientMain {

    private static final String URL = "https://en.wikipedia.org/wiki/URL";

    public static void main(String[] args) throws IOException {

        Client client = new Client("127.0.1.1", 5000);
        client.sendMessage(URL);
        System.out.println(client.readMessage());
        client.close();
    }
}

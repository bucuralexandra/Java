package com.nagarro.remotelearning.week15.p1;

import java.io.IOException;

public class ServerMain {

    public static void main(String args[]) throws IOException {

        Server server = new Server(5000);
        server.close();
    }
}

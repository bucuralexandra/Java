package com.nagarro.remotelearning.week15.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private BufferedReader in = null;
    private PrintWriter writer;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String messageFromClient = in.readLine();
            writer.println(Reader.readPage(messageFromClient));

        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public void close() throws IOException {
        System.out.println("Closing connection");
        socket.close();
        in.close();
    }
}

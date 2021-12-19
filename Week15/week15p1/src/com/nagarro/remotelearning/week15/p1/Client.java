package com.nagarro.remotelearning.week15.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        System.out.println("Client sending the message...");
        writer.println(message);
        System.out.println("Message sent");
    }

    public String readMessage() throws IOException {
        System.out.println("Client reading the message...");
        String line = reader.readLine();
        StringBuilder message = new StringBuilder();
        message.append(line);
        while (line != null && !line.equals("")) {
            line = reader.readLine();
            message.append(line);
        }
        System.out.println("Message read");
        return message.toString();
    }

    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();

    }
}

package com.nagarro.remotelearning.week12.p1;

import java.util.Random;

public class Sender implements Runnable {
    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private TransmittedData data;
    private String message;
    private static final Random rnd = new Random();

    public Sender(TransmittedData data) {
        this.data = data;
    }

    private void generateRandomMessage() {
        StringBuilder builder = new StringBuilder();
        int length = rnd.nextInt(3) + 3;
        for (int i = 0; i < length; i++) {
            builder.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
        }
        this.message = builder.toString();
    }

    @Override
    public void run() {
        int nrMessages = 10;
        while (nrMessages > 0) {
            generateRandomMessage();
            System.out.println(message);
            data.put(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            nrMessages--;
        }
    }
}

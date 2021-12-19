package com.nagarro.remotelearning.week12.p1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TransmittedData {
    private BlockingQueue<String> messages = new LinkedBlockingQueue<>();
    private static final int CAPACITY = 10;


    public synchronized String take() { //take
        while (messages.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String currentMessage = messages.poll();
        notifyAll();
        return currentMessage;
    }

    public synchronized void put(String message) {
        while (messages.size() == CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        messages.add(message);
        notifyAll();
    }

    public BlockingQueue<String> getMessages() {
        return messages;
    }
}

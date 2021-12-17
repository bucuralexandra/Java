package org.example.Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Client> clients;
    private final AtomicInteger waitingPeriod;

    public Server() {
        this.clients = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Client newClient) {
        this.clients.add(newClient);
        this.waitingPeriod.addAndGet(newClient.getServiceTime());
    }

    @Override
    public void run() {
        int sleepPeriod = 1;
        if (this.clients.peek() != null) {
            sleepPeriod = this.clients.peek().getServiceTime();
        }
        while (true) {
            try {
                this.waitingPeriod.decrementAndGet();
                Thread.sleep(1000 * sleepPeriod);
            } catch (InterruptedException e) {
                System.out.println("Something woke me");
            }
            if (clients.peek() != null && clients.peek().getServiceTime() == 0) {
                clients.remove();
            }
        }
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    @Override
    public String toString() {
        if (this.clients != null) {
            if ((this.clients.peek() != null && this.clients.peek().getServiceTime() == 0))
                this.clients.remove();
            if (this.clients.isEmpty()) {
                return "closed";
            } else {
                StringBuilder s = new StringBuilder();
                for (Client client : this.clients) {
                    s.append(client.toString()).append(" ");
                }
                return s.toString();
            }
        }
        return "";
    }
}

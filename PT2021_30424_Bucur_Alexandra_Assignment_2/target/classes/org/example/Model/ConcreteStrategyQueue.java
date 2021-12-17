package org.example.Model;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addClient(List<Server> queues, Client p) {
        int minLength = Integer.MAX_VALUE;
        int whichQueue = 0;
        if (queues != null) {
            for (Server s : queues) {
                if (s != null) {
                    int size = s.getClients().size();
                    if (size > 0 && s.getClients().peek().getServiceTime() == 0)
                        size--;
                    if (size < minLength) {
                        minLength = s.getClients().size();
                        whichQueue = queues.indexOf(s);
                    }
                }
            }
            queues.get(whichQueue).addTask(p);
        }
    }

    public static int strategyQueue(List<Server> queues, Client p) {
        int minLength = Integer.MAX_VALUE;
        int whichQueue = 0;
        if (queues != null) {
            for (Server s : queues) {
                if (s != null) {
                    int size = s.getClients().size();
                    if (size > 0 && s.getClients().peek().getServiceTime() == 0)
                        size--;
                    if (size < minLength) {
                        minLength = s.getClients().size();
                        whichQueue = queues.indexOf(s);
                    }
                }
            }
            return whichQueue;
        }
        return -1;
    }
}

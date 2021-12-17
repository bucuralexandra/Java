package org.example.Model.StrategyManager;

import org.example.Model.Client;
import org.example.Model.Simulation.Server;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addClient(List<Server> queues, Client p) {
        int minTime = Integer.MAX_VALUE;
        int whichQueue = 0;
        if( queues != null) {
            for (Server s : queues) {
                if( s != null) {
                    if (s.getWaitingPeriod().get() < minTime) {
                        minTime = s.getWaitingPeriod().get();
                        whichQueue = queues.indexOf(s);
                    }
                }
            }
            queues.get(whichQueue).addTask(p);
        }
    }

    public static int strategyTime(List<Server> queues, Client p) {
        int minTime = Integer.MAX_VALUE;
        int whichQueue = 0;
        if( queues != null) {
            for (Server s : queues) {
                if( s != null) {
                    if (s.getWaitingPeriod().get() < minTime) {
                        minTime = s.getWaitingPeriod().get();
                        whichQueue = queues.indexOf(s);
                    }
                }
            }
           return whichQueue;
        }
        return -1;
    }
}

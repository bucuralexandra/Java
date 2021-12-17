package org.example.Model.Simulation;

import org.example.Model.Client;
import org.example.Model.StrategyManager.ConcreteStrategyQueue;
import org.example.Model.StrategyManager.ConcreteStrategyTime;
import org.example.Model.StrategyManager.SelectionPolicy;
import org.example.Model.StrategyManager.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Server> queues;
    private int maxNrServers;
    private int maxTasksPerServer; //number of clients
    private Strategy strategy;

    public Scheduler(int maxNrServers, int maxTasksPerServer, SelectionPolicy policy) {
        this.maxNrServers = maxNrServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.queues = new ArrayList<>();
        decideOnStrategy(policy);
        for (int i = 0; i <this.maxNrServers; i++){
            Server aux = new Server();
            this.queues.add(aux);
            Thread thread = new Thread(aux);
            thread.start();
        }
    }
    public void decideOnStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }
    public void  dispatchTask(Client t){
        strategy.addClient(queues,t);
    }

    public List<Server> getQueues(){
        return queues;
    }

    public boolean emptyServiceQueues(){
        boolean returnValue = true;
        for (Server server: this.queues){
            if (!server.getClients().isEmpty())
                returnValue = false;
        }
        return returnValue;
    }
    @Override
    public String toString() {
       StringBuilder s = new StringBuilder();
       int currenQueue = 1;
       if(queues != null) {
           for (Server queue : this.queues) {
               s.append("Queue " + currenQueue + " :");
               currenQueue++;
               s.append(queue.toString());
               s.append("\n");
           }
           return s.toString();
       }
       return "";
    }

    public int allClients() {
        int all = 0;
        if(this.queues != null) {
            for (Server current : queues) {
                all += current.getClients().size();
            }
        }
        return all;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}

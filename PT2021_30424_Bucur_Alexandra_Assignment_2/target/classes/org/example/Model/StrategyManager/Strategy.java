package org.example.Model.StrategyManager;

import org.example.Model.Client;
import org.example.Model.Simulation.Server;

import java.util.List;

public interface Strategy {
    void addClient(List<Server> queues, Client p);
}

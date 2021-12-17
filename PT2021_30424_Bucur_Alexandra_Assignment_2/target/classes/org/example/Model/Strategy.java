package org.example.Model;

import java.util.List;

public interface Strategy {
    void addClient(List<Server> queues, Client p);
}

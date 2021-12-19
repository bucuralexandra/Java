package com.nagarro.remotelearning.week12.p1;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        TransmittedData data = new TransmittedData();


        Thread client1 = new Thread(new Sender(data));
        Thread client2 = new Thread(new Sender(data));
        Thread receiver = new Thread(new Server(data));
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(15);
        executorService.scheduleAtFixedRate(receiver, 1, 2L, TimeUnit.SECONDS);

        client1.start();
        client2.start();
        receiver.start();
    }
}

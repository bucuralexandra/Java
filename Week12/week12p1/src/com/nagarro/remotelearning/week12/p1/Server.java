package com.nagarro.remotelearning.week12.p1;

public class Server implements Runnable {

    private TransmittedData load;

    public Server(TransmittedData data) {
        this.load = data;
    }

    @Override
    public void run() {
        while (load.getMessages().size() > 0) {
            String receivedMessage = load.take();
            System.out.println(receivedMessage);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

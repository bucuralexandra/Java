package com.nagarro.remotelearning.week12.p2;


public class ThreadRaceCompetitor extends Thread {
    private int ID;

    public ThreadRaceCompetitor(int id) {
        this.ID = id;
    }

    public void run() {
        System.out.println("Thread with id " + ID + "is competing");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadRaceContext.terminatedThread(this);
    }

    public int getID() {
        return ID;
    }
}

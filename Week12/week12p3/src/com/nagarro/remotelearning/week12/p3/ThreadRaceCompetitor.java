package com.nagarro.remotelearning.week12.p3;


public class ThreadRaceCompetitor extends Thread {
    private int ID;
    private String teamName;

    public ThreadRaceCompetitor(int ID, String teamName) {
        this.ID = ID;
        this.teamName = teamName;
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getID() {
        return ID;
    }
}

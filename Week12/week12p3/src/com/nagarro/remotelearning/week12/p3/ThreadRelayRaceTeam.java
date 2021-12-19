package com.nagarro.remotelearning.week12.p3;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadRelayRaceTeam extends Thread {

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final static int NR_TEAMMATES = 4;
    private String name;
    private BlockingQueue<ThreadRaceCompetitor> teammates = new LinkedBlockingQueue<>();
    private final static Random rnd = new Random();

    public ThreadRelayRaceTeam() {
        generateRandomTeamName();
        for (int i = 0; i < NR_TEAMMATES; i++) {
            ThreadRaceCompetitor threadRaceCompetitor = new ThreadRaceCompetitor(i, this.name);
            teammates.add(threadRaceCompetitor);
        }
    }

    private void generateRandomTeamName() {
        StringBuilder builder = new StringBuilder();
        int length = rnd.nextInt(3) + 3;
        for (int i = 0; i < length; i++) {
            builder.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
        }
        this.name = builder.toString();
    }

    public void run() {
        try {
            for (ThreadRaceCompetitor competitor : teammates) {
                competitor.start();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadRaceContext.showFinishedTeam(this);
    }

    public String getNameOfTeam() {
        return name;
    }
}

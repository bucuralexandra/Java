package com.nagarro.remotelearning.week12.p2;


import java.util.concurrent.LinkedBlockingQueue;

public class ThreadRaceContext {

    private LinkedBlockingQueue<Integer> scorecard = new LinkedBlockingQueue();

    public static void terminatedThread(ThreadRaceCompetitor threadRaceCompetitor) {
        System.out.println("Competitor " + threadRaceCompetitor.getID() + "finished");
    }

    public String getScorecard() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : this.scorecard) {
            stringBuilder.append("Competitor " + integer + " finished");
        }
        return stringBuilder.toString();
    }
}

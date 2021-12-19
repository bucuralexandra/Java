package com.nagarro.remotelearning.week12.p2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadRace {

    private final static int NUMBER_OF_COMPETITORS = 10;
    private BlockingQueue<ThreadRaceCompetitor> competitors = new LinkedBlockingQueue<>();

    private ThreadRaceContext threadRaceContext = new ThreadRaceContext();

    public ThreadRace() throws InterruptedException {
        for (int i = 0; i < NUMBER_OF_COMPETITORS; i++) {
           ThreadRaceCompetitor threadRaceCompetitor = new ThreadRaceCompetitor(i);
            competitors.add(threadRaceCompetitor);
            threadRaceCompetitor.start();
        }
        for(Thread thread: competitors){
            thread.join();
        }
        System.out.println(threadRaceContext.getScorecard());
    }

    public BlockingQueue<ThreadRaceCompetitor> getCompetitors() {
        return competitors;
    }

}

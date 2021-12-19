package com.nagarro.remotelearning.week12.p3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadRelayRace {

    private final static int NUMBER_OF_TEAMS = 10;
    private BlockingQueue<ThreadRelayRaceTeam> teams = new LinkedBlockingQueue<>();

    public ThreadRelayRace() {
        for (int i = 0; i < NUMBER_OF_TEAMS; i++) {
            ThreadRelayRaceTeam thread = new ThreadRelayRaceTeam();
            teams.add(thread);
            thread.start();
        }
    }

    public BlockingQueue<ThreadRelayRaceTeam> getCompetitors() {
        return teams;
    }
}

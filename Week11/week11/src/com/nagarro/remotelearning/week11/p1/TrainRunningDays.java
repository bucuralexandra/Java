package com.nagarro.remotelearning.week11.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainRunningDays {

    private List<Integer> days = new ArrayList<>();
    private final Random rnd = new Random();
    private final static int DAYS = 365;

    public TrainRunningDays() {
        int length = rnd.nextInt(DAYS) + 1;
        for (int i = 0; i < length; i++) {
            days.add(rnd.nextInt(DAYS) + 1);
        }
    }
}

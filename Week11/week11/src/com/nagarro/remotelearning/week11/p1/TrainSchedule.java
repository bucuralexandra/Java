package com.nagarro.remotelearning.week11.p1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TrainSchedule {

    private HashMap<Train, TrainRunningDays> trainHashMap = new HashMap<>();
    private List<Train> trainTestList = new ArrayList<>();
    private static final int MAX_CAPACITY = 10000;
    private static final int FIRST_TEST = 100;
    private static final int SECOND_TEST = 1000;

    public TrainSchedule() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            Train train = new Train();
            saveKeys(i, train);
            TrainRunningDays d = new TrainRunningDays();
            trainHashMap.put(train, d);
        }
    }

    private void saveKeys(int index, Train train) {
        if (index == FIRST_TEST) {
            trainTestList.add(train);
        }
        if (index == SECOND_TEST) {
            trainTestList.add(train);
        }
        if (index + 1 == MAX_CAPACITY) {
            trainTestList.add(train);
        }
    }

    public HashMap<Train, TrainRunningDays> getTrainHashMap() {
        return trainHashMap;
    }

    public List<Train> getTrainTestList() {
        return trainTestList;
    }
}

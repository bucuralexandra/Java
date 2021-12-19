package com.nagarro.remotelearning.week11.p1;

import java.util.Objects;
import java.util.Random;

public class Train {
    private int trainNr;
    private TrainType trainType;
    private int nrWagons;
    private final static Random rnd = new Random();
    private final static int MAX_NR_TRAIN = 20000;
    private final static int MAX_NR_WAGONS = 100;


    public Train(int trainNr, TrainType trainType, int nrWagons) {
        this.trainNr = trainNr;
        this.trainType = trainType;
        this.nrWagons = nrWagons;
    }

    public Train() {
        this.trainNr = rnd.nextInt(MAX_NR_TRAIN);
        this.nrWagons = rnd.nextInt(MAX_NR_WAGONS);
        this.trainType = TrainType.values()[rnd.nextInt(TrainType.values().length)];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return nrWagons == train.nrWagons && trainType == train.trainType && Objects.equals(train, train.trainNr);
    }

    /*@Override
    public int hashCode() {
        return 8;
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(trainNr, trainType, nrWagons);
    }
}

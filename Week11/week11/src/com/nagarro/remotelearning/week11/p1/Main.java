package com.nagarro.remotelearning.week11.p1;

public class Main {

    public static final int MILLISECONDS_TRANSFORMATION = 1000000;

    public static void main(String[] args) {

        double start = System.nanoTime();
        TrainSchedule trainSchedule = new TrainSchedule();
        double finish = System.nanoTime();
        double timeElapsed = finish - start;
        System.out.println("Insert all keys time: " + timeElapsed / MILLISECONDS_TRANSFORMATION);

        for (Train train : trainSchedule.getTrainTestList()) {
            start = System.nanoTime();
            trainSchedule.getTrainHashMap().get(train);
            finish = System.nanoTime();
            timeElapsed = finish - start;
            System.out.println(timeElapsed / MILLISECONDS_TRANSFORMATION);
        }

/**
 *  I am not sure if I should create 2 classes of the same type, but with different hasCodes or not, but I can put here
 *  the results for the the hashCode that always returns a constant value
 *  Total time of insertions : 1066.4496
 * 0.8735
 * 0.5724
 * 0.3134
 *
 * (results are in milliseconds)
 * And these are the results for a hashCode that does not always give the same hashKey.
 *  Total time of insertions : 87.7507
 * 0.0074
 * 0.0028
 * 0.0013
 *
 * I testet at time 100,1000,10.000 for both cases. The time(in milliseconds) is smaller for the
 * second case(hashCode which returns different values) because there are quite less collisions; by this I mean that
 * for the first case all the values would be put on the 8th position, which results in collissions that take more time
 * to resolve(or to add them in the hashMap). For the second case there are less collisions,
 * (in the perfect case none) so when we want to find a certain
 * element we can find it from the first try, or among the first ones => constant time; case that does not happen
 * for the first hashCode implementation where we would have to iterate through all values until we get
 * the desired one => a complexity of O(logn) because Java uses a tree instead of a simple linkedList for
 * the values stored in the same bucket
 */
    }
}

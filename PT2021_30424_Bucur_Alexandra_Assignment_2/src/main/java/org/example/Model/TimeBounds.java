package org.example.Model;

public class TimeBounds {

    private int minTime;
    private int maxTime;

    public TimeBounds() {
        this.maxTime = 0;
        this.minTime = 0;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
}

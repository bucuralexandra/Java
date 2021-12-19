package com.iquestgroup.remotelearning.week3.p3.Alexandra;

public class Drumset extends Instrument{

    private String sticks;
    private static final String DRUM = "Drums are playing";

    public Drumset(String name, double price, String sticks) {
        super(name, price);
        this.sticks = sticks;
    }

    public String getSticks() {
        return sticks;
    }

    public void setSticks(String sticks) {
        this.sticks = sticks;
    }

    @Override
    public String playInstrument() {
        return DRUM;
    }
}

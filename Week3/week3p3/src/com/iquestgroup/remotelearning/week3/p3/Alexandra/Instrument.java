package com.iquestgroup.remotelearning.week3.p3.Alexandra;

public class Instrument {
    private String name;
    private double price;

    public Instrument(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String playInstrument(){
        return "Instrument " + this.name + " is playing";
    }
}

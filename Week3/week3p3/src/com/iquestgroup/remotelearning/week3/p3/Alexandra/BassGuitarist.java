package com.iquestgroup.remotelearning.week3.p3.Alexandra;

public class BassGuitarist extends Guitarist{

    private String amplifier;

    public BassGuitarist(String name, int age, Guitar guitar, String amplifier) {
        super(name, age, guitar);
        this.amplifier = amplifier;
    }

    public String getAmplifier() {
        return amplifier;
    }

    @Override
    public void play() {
        System.out.println("Bass_guitarist " + this.getName() + "took the guitar");
        this.getGuitar().playInstrument();
    }
}

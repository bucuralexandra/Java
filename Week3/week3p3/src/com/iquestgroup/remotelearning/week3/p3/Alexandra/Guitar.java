package com.iquestgroup.remotelearning.week3.p3.Alexandra;

public class Guitar extends Instrument{

    private static final String PLAY =  "Guitar is playing";
    private String picks;

    public Guitar(String name, double price, String picks) {
        super(name, price);
        this.picks = picks;
    }

    public String getPicks() {
        return picks;
    }

    public void setPicks(String picks) {
        this.picks = picks;
    }

    @Override
    public String playInstrument() {
        return PLAY ;
    }
}

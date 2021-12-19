package com.iquestgroup.remotelearning.week3.p3.Alexandra;

public class Drummer extends Musician implements Playing{

    private static final String PRACTISE_DRUMMER = "Drummer is now practising";
    private Drumset drumset;

    public Drummer(String name, int age, Drumset drumset) {
        super(name, age);
        this.drumset = drumset;
    }

    @Override
    public void play() {
        System.out.println(this.getName() + " is playing drums");
        System.out.println(this.drumset.playInstrument());
    }

    @Override
    public void practise() {
        System.out.println(PRACTISE_DRUMMER);
    }

    public Drumset getDrumset() {
        return drumset;
    }

    public void setDrumset(Drumset drumset) {
        this.drumset = drumset;
    }
}

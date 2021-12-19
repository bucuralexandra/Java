package com.iquestgroup.remotelearning.week3.p3.Alexandra;

public class Guitarist extends Musician implements Playing{

    private static final String PRACTISE_GUITARIST = "Guitarist is now practising";
    private Guitar guitar;

    public Guitarist(String name, int age, Guitar guitar) {
        super(name, age);
        this.guitar = guitar;
    }

    @Override
    public void practise() {
        System.out.println(PRACTISE_GUITARIST);
    }


    public void play(){
        System.out.println(this.getName() +  " is playing guitar");
        System.out.println(guitar.playInstrument());
    }

    public Guitar getGuitar() {
        return guitar;
    }
}

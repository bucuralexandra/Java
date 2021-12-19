package com.nagarro.remotelearning.week16.p2;

public class Pigeon extends Bird {

    private String Type; //intentionally put with uppercase to show the checking is done correctly

    public Pigeon(String name, int age, int numberLegs, double wingSpan, String type) {
        super(name, age, numberLegs, wingSpan);
        this.Type = type;
    }

    @Override
    public void makeSound() {
        System.out.println("Pigeon makes COOCOO COOCOO!");
    }
}

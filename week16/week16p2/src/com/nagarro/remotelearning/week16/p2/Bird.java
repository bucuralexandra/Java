package com.nagarro.remotelearning.week16.p2;

public class Bird extends Animal {

    private double wingSpan;

    public Bird(String name, int age, int numberLegs, double wingSpan) {
        super(name, age, numberLegs);
        this.wingSpan = wingSpan;
    }

    @Override
    public void setNumberLegs(int numberLegs) {
        this.setNumberLegs(2);
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Bird is flying");
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("Bird is picking food");
    }

    @Override
    public void makeSound() {
        super.makeSound();
        System.out.println("Bird: CRAAA CRAAA");
    }
}

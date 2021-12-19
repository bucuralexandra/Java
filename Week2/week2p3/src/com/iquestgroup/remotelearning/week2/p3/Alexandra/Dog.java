package com.iquestgroup.remotelearning.week2.p3.Alexandra;

public class Dog extends Animal {

    private int eyes;
    private int legs;
    private int tail;
    private String coat;

    public Dog(String name, String size, int weight, int eyes, int legs, int tail, String coat) {
        super(name, size, weight);
        System.out.println("Dog constructor is continued with the other parameters");
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
        this.coat = coat;
    }

    private void chew() {
        System.out.println("Dog.chew() called");
    }

    @Override
    public void eat() {
        System.out.println("Dog.eat() called");
        chew();
        super.eat();
    }

    public void walk() {
        System.out.println("Dog.walk() called");
        super.move(5);
    }

    public void run() {
        System.out.println("Dog.run() called");
        move(10);
    }

    private void moveLegs(int speed) {
        System.out.println("Dog.moveLegs() called");
    }

    @Override
    public void move(int speed) {
        System.out.println("Dog.move() called");
        moveLegs(speed);
        super.move(speed);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + this.getName() +
                '}';
    }
}

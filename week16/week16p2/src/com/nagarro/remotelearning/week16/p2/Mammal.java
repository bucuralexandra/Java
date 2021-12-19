package com.nagarro.remotelearning.week16.p2;

public class Mammal extends Animal {

    private String typeOfMammal;

    public Mammal(String name, int age, int numberLegs, String typeOfMammal) {
        super(name, age, numberLegs);
        this.typeOfMammal = typeOfMammal;
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("Mammal is eating");
    }

    @Override
    public void makeSound() {
        super.makeSound();
        System.out.println("Mammal is making a sound");
    }
}

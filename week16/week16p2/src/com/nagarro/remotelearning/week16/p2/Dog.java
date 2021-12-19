package com.nagarro.remotelearning.week16.p2;

public class Dog extends Mammal {

    private boolean hasOwner;
    private String furColor;
    public String breedName; //put it here by choice in order to see that the method works

    public Dog(String name, int age, int numberLegs, String typeOfMammal, boolean hasOwner, String furColor, String breedName) {
        super(name, age, numberLegs, typeOfMammal);
        this.hasOwner = hasOwner;
        this.furColor = furColor;
        this.breedName = breedName;
    }

    @Override
    public void makeSound() {
        super.makeSound();
        System.out.println("Dog: HAM HAM HAM");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Dog is running");
    }
}

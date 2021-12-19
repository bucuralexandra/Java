package com.nagarro.remotelearning.week16.p2;

public class Animal {

    private String name;
    private int age;
    private int numberLegs;

    public Animal(String name, int age, int numberLegs) {
        this.name = name;
        this.age = age;
        this.numberLegs = numberLegs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberLegs() {
        return numberLegs;
    }

    public void setNumberLegs(int numberLegs) {
        this.numberLegs = numberLegs;
    }

    public void move() {
        System.out.println("Animal is moving");
    }

    public void eat() {
        System.out.println("Animal is eating");
    }

    public void makeSound() {
        System.out.println("Animal is making a sound");
    }
}

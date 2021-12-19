package com.iquestgroup.remotelearning.week2.p3.Alexandra;

public class Animal {

    static final String message = "All animals are beautiful";

    static {
        System.out.println("Static blocked called");
    }

    private String name;
    private String size;
    private int weight;

    public Animal(String name, String size, int weight) {
        System.out.println("Animal constructor is called");
        this.name = name;
        this.size = size;
        this.weight = weight;
    }

    public void eat() {
        System.out.println("Animal.eat() called");
    }

    public void rest() {
        System.out.println("Animal.rest() called");
    }

    public void move(int speed) {
        System.out.println("Animal.move() called.  Animal is moving at " + speed);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }
}

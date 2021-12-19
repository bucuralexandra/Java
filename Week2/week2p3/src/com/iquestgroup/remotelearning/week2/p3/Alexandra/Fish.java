package com.iquestgroup.remotelearning.week2.p3.Alexandra;

public class Fish extends Animal {

    private int gills;
    private int eyes;
    private int fins;

    public Fish(String name, String size, int weight, int gills, int eyes, int fins) {

        super(name, size, weight);
        System.out.println("Fish constructor is continued");
        this.gills = gills;
        this.eyes = eyes;
        this.fins = fins;
    }

    @Override
    public void rest() {
        System.out.println("Fish tired, rest now");
    }

    private void moveMuscles() {
        System.out.println("Fish.moveMuscle() is calLed");
        System.out.println("Fish is moving muscles");
    }

    private void moveBackFin() {
        System.out.println("Fish.moveBackFin() is called");
        System.out.println("Moving back fin");
    }

    public void swim(int speed) {
        moveMuscles();
        moveBackFin();
        super.move(speed);
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name='" + this.getName() + '\'' +
                ", size='" + this.getSize() + '\'' +
                ", weight=" + this.getWeight() +
                '}';
    }
}

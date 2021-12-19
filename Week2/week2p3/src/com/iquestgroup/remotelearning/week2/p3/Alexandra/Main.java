package com.iquestgroup.remotelearning.week2.p3.Alexandra;

public class Main {

    public static void main(String[] args) {

        System.out.println(Animal.message);
        Animal animal = new Animal("Animal", "very big", 5);
        System.out.println(animal.toString());
        animal.eat();
        animal.move(3);
        System.out.println("Name is = " + animal.getName());
        animal.rest();

        System.out.println("-----------------------------------");
        Dog dog = new Dog("Billie",
                "moderate",
                20,
                2,
                4,
                1,
                "long silky");
        System.out.println(dog.toString());
        dog.eat();
        dog.walk();
        dog.run();
        System.out.println("Name is = " + dog.getName());
        dog.rest();
        dog.move(2);

        System.out.println("-----------------------------------");
        Fish fish = new Fish("Nemo", "small", 10, 100, 2, 2);
        System.out.println(fish.toString());
        System.out.println("Name = " + fish.getName());
        fish.rest();
        fish.swim(2);
        fish.eat();

    }
}


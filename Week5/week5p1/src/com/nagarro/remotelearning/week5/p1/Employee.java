package com.nagarro.remotelearning.week5.p1;

public class Employee {

    private String name;
    private int age;
    private final String message = "Illegal age to work";

    public Employee(String name, int age) {
        this.name = name;
        if (age >= 18) {
            this.age = age;
        } else {
            throw new InvalidAgeException(message);
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

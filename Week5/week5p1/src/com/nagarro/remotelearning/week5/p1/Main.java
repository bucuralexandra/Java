package com.nagarro.remotelearning.week5.p1;

public class Main {

    public static void main(String[] args) {

        try {
            Employee e1 = new Employee("Albert", 23);
        } catch (InvalidAgeException e) {
            e.printStackTrace();
        }

        try {
            Employee e2 = new Employee("Tom", 15);
        } catch (InvalidAgeException e) {
            e.printStackTrace();
        }
    }
}

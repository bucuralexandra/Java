package com.nagarro.remotelearning.week16.p1;

import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        FileReader fileReader = new FileReader("person.txt");
        LinkedHashSet<Person> listOfPersonalities = fileReader.getDataFromFile();
        for (Person person : listOfPersonalities) {
            System.out.println(person.toString());
        }
    }
}

package com.iquestgroup.remotelearning.week1.p1.Alexandra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

public class DataBase {

    private LinkedHashSet<Person> listPersonalities;
    private String filename;


    public DataBase(String filename) {

        listPersonalities = new LinkedHashSet<>();
        this.filename = filename;
    }

    public void readFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] elems = line.split(", ");
                try {
                    Person p;
                    if (elems[2].contains("b.")) {
                        String firstName = elems[0];
                        String lastName = elems[1];
                        elems[2] = elems[2].substring(2);
                        int dateBirth = Integer.parseInt(elems[2]);
                        p = new Person(firstName, lastName, dateBirth);
                    } else {
                        String firstName = elems[0];
                        String lastName = elems[1];
                        int dateBirth = Integer.parseInt(elems[2]);
                        int dateDeath = Integer.parseInt(elems[3]);
                        p = new Person(firstName, lastName, dateBirth, dateDeath);
                    }
                    listPersonalities.add(p);

                } catch (NumberFormatException ex) {
                    System.err.println("Invalid data " + ex);
                }
            }
        } catch (IOException ex) {
            System.err.println("Error reading");
        }
    }

    public void showPersonalities() {
        for (Person index : listPersonalities) {
            System.out.println(index.toString());
        }
    }
}

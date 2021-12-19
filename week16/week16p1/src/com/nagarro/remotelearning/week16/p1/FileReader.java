package com.nagarro.remotelearning.week16.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class FileReader {

    private String filename;


    public FileReader(String filename) {
        this.filename = filename;
    }

    public LinkedHashSet<Person> getDataFromFile() throws ClassNotFoundException {

        LinkedHashSet<Person> listFromFile = new LinkedHashSet<>();
        String line;

        Field[] fields;
        Class<?> personClass = Class.forName("com.nagarro.remotelearning.week16.p1.Person");
        fields = personClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                String[] elems = line.split(", ");
                try {
                    Person person = (Person) personClass.getConstructor().newInstance();
                    if (elems[2].contains("b.")) {
                        createAlivePerson(fields, elems, person);
                    } else {
                        createDeadPerson(fields, elems, person);
                    }
                    listFromFile.add(person);
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid data " + ex);
                } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading");
        }
        return listFromFile;
    }

    private void createDeadPerson(Field[] fields, String[] elems, Person person) throws IllegalAccessException {
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            if (fieldName.equals("dateOfBirth")) {

                fields[i].set(person, Integer.parseInt(elems[2]));
            } else {
                if (fieldName.equals("dateOfDeath")) {
                    fields[i].set(person, Integer.parseInt(elems[3]));
                } else {
                    fields[i].set(person, elems[i]);
                }
            }
        }
    }

    private void createAlivePerson(Field[] fields, String[] elems, Person person) throws IllegalAccessException {
        for (int i = 0; i < fields.length - 1; i++) {
            String fieldName = fields[i].getName();
            if (fieldName.equals("dateOfBirth")) {
                elems[2] = elems[2].substring(2);
                fields[i].set(person, Integer.parseInt(elems[2]));
                fields[3].set(person, -1);
            } else {
                fields[i].set(person, elems[i]);
            }
        }
    }
}

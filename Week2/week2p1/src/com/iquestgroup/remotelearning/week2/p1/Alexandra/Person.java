package com.iquestgroup.remotelearning.week2.p1.Alexandra;

public class Person {
    private final String NULL = "No name has been introduced";
    private String firstname;
    private String surname;

    public Person(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }

    public Person(String fullname) {

        if( fullname == null) {
            this.firstname = this.surname = "";
            System.out.println(NULL);
        }
        String[] parts = fullname.split(" ");
        this.firstname = processName(parts.length, parts);
        this.surname = parts[parts.length - 1];
    }

    public void showPerson() {
        System.out.println("Person{" +
                "firstname='" + firstname + '\'' +
                ", lastName='" + surname + '\'' +
                '}');
    }

    private String processName(int nrNames, String parts[]) {

        StringBuilder first = new StringBuilder();
        int i = 0;
        while (i < nrNames - 1) {

            first.append(" " + parts[i]);
            i++;
        }
        return first.toString();
    }
}

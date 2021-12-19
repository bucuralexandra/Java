package com.iquestgroup.remotelearning.week1.p1.Alexandra;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private int dateOfBirth;
    private int dateOfDeath;


    public Person(String firstName, String lastName, int dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = -1; //still alive
    }

    public Person(String firstName, String lastName, int dateOfBirth, int dateOfDeath) {
        this(firstName, lastName, dateOfBirth);

        this.dateOfDeath = dateOfDeath;
    }


    @Override
    public String toString() {
        if (this.dateOfDeath == -1)
            return "(" + firstName + " " + lastName + " " + dateOfBirth + "-present)";
        else
            return "(" + firstName + " " + lastName + " " + dateOfBirth + "-" + dateOfDeath + ")";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return dateOfBirth == person.dateOfBirth && dateOfDeath == person.dateOfDeath
                && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, dateOfDeath);
    }
}

package com.nagarro.remotelearning.week16.p1;

import java.util.Objects;

public class Person {

    private String firstName;
    private String lastName;
    private int dateOfBirth;
    private int dateOfDeath;

    public Person() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(int dateOfDeath) {
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

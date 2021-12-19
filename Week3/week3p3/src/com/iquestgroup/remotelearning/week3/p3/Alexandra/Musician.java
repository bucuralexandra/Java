package com.iquestgroup.remotelearning.week3.p3.Alexandra;

import java.util.ArrayList;
import java.util.Objects;

public class Musician implements Playing{
    private static final String BAND = "Already in a band";
    private String name;
    private int age;
    private Band band;

    public Musician(String name, int age) {
        this.name = name;
        this.age = age;
        this.band = null;
    }

    public Musician(String name, int age, Band band) {
      this(name,age);
        this.band = band;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }


    public void changeBand(Band band){
        this.band.removeMember(this);
        this.band = band;
    }
    public void joinBand(Band band){
        if(this.band == null){
            this.band = band;
        }
        else {
            System.out.println(BAND);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musician musician = (Musician) o;
        return age == musician.age && Objects.equals(name, musician.name) && Objects.equals(band, musician.band);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, band);
    }

    public void leaveBand(){
        if(band.isInBand(this) == true){
            this.band.removeMember(this);
            changeBand(null);

        }
        else{
            System.out.println("Musician not in band " + band.getName());
        }
    }

    @Override
    public void play() {
        System.out.println("Musician is playing");
    }

    @Override
    public void practise() {
        System.out.println("Now practising");
    }
}

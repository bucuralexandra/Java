package com.nagarro.remotelearning.week10.p1;

public class Country implements Comparable {

    private String name;
    private String capital;

    public Country(String name, String capital) {
        this.name = name;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    @Override
    public int compareTo(Object o) {
        if (this.getClass() != o.getClass()) {
            return -1;
        }
        Country c = (Country) o;
        if (this.name.equals(c.name)){
            return 0;
        }
        return this.name.compareTo(c.name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                '}' ;
    }

}


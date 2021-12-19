package com.nagarro.remotelearning.week10.p1;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("Spain", "Madrid"));
        countries.add(new Country("Romania", "Bucharest"));
        countries.add(new Country("Bulgaria", "Sofia"));
        countries.add(new Country("Italy", "Rome"));
        countries.add(new Country("France", "Paris"));

        countries.forEach((c) -> {
            System.out.println(c );
        });
        System.out.println();
        Collections.sort(countries);

        countries.forEach((c) -> {
            System.out.println(c );
        });
        System.out.println();
        Collections.sort(countries, new CapitalComparator());
        countries.forEach((c) -> {
            System.out.println(c );
        });

        System.out.println(CountrySearcher.binarySearch("Paris", countries));

    }
}

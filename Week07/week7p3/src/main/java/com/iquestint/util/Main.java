package com.iquestint.util;

public class Main {

    public static void main(String[] args) {

        StringTitlelizer stringTitlelizer = new StringTitlelizer();

        String s = "Ana are the mere";
        System.out.println(stringTitlelizer.titlelize(s));

        String s2 = "Now is the time to act! \nso what are you Waiting for? a a a the \nthe";
        System.out.println(stringTitlelizer.titlelize(s2));
    }
}
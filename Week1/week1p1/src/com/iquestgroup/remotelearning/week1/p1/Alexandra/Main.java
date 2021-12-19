package com.iquestgroup.remotelearning.week1.p1.Alexandra;

public class Main {


    public static void main(String[] args) {

        DataBase db = new DataBase("person.txt");
        db.readFile();
        db.showPersonalities();

    }
}

package com.nagarro.remotelearning.week7.p5;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a string");
        String sentence = keyboard.nextLine();
        System.out.println("Sentence that you introduced: " + sentence);
        PigLatin pigLatin = new PigLatin();
        pigLatin.toPigLatin(sentence);
    }
}
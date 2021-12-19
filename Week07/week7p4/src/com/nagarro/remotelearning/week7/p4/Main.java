package com.nagarro.remotelearning.week7.p4;

public class Main {

    public static void main(String[] args) {

        RandomSentenceGenerator randomSentenceGenerator = new RandomSentenceGenerator();

        randomSentenceGenerator.createSentences();
        System.out.println();
        randomSentenceGenerator.createStory();
    }
}
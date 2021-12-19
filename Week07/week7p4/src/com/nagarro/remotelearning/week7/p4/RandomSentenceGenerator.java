package com.nagarro.remotelearning.week7.p4;

import java.util.Random;

public class RandomSentenceGenerator {
    private static final String[] article = {"the", "a", "one", "some", "any"};
    private static final String[] noun = {"boy", "girl", "dog", "town", "car"};
    private static final String[] verb = {"drove", "jumped", "ran", "walked", "skipped"};
    private final static String[] preposition = {"to", "from", "over", "under", "on"};
    private static StringBuilder[] sentences;
    private final static Random rnd = new Random();

    public RandomSentenceGenerator() {
        sentences = new StringBuilder[21];
        for (int i = 0; i < 20; i++) {
            sentences[i] = new StringBuilder();
        }
    }

    private String makeSentence() {

        int number = 0;
        StringBuilder sentence = new StringBuilder();

        sentence.append(pickRandomWord(article, number, true));
        sentence.append(pickRandomWord(noun, number, true));
        sentence.append(pickRandomWord(verb, number, true));
        sentence.append(pickRandomWord(preposition, number, true));
        sentence.append(pickRandomWord(article, number, true));
        sentence.append(pickRandomWord(noun, number, false));

        StringBuilder firstLetter = new StringBuilder();
        firstLetter.append(sentence.substring(0, 1).toUpperCase());
        StringBuilder remainingWord = new StringBuilder();
        remainingWord.append(sentence.substring(1, sentence.length()));

        return ((firstLetter.append(remainingWord)).toString());
    }

    public void createSentences() {

        for (int i = 0; i < 20; i++) {
            sentences[i].append(makeSentence());
            System.out.println("Sentence " + i + ": " + sentences[i]);
        }
    }

    public void createStory() {

        int numberSentences = rnd.nextInt(20);
        int indexSentence;
        for (int i = 0; i < numberSentences; i++) {
            indexSentence = rnd.nextInt(20);
            System.out.print(sentences[indexSentence] + " ");
        }
    }

    private String pickRandomWord(String[] list, int number, boolean spaceNeeded) {

        number = rnd.nextInt(5);
        return spaceNeeded ? list[number] + " " : list[number] + ".";
    }
}

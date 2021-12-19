package com.iquestint.util;

import java.util.List;

public class StringTitlelizer implements Titlelizer {

    private final static List skippedWords = List.of("the", "a", "to", "of", "is", "in");

    public String titlelize(String toTitlelize) {

        if (toTitlelize == null) {
            throw new IllegalArgumentException("Null String");
        }
        if (toTitlelize.isBlank()) {
            return "";
        }
        String[] componentsToTitlelize = toTitlelize.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : componentsToTitlelize) {
            if (!wordToSkip(s)) {
                sb.append(Character.toUpperCase(s.charAt(0)))
                        .append(s.substring(1)).append(" ");
            } else {
                sb.append(s).append(" ");
            }
        }
        return sb.toString().trim();
    }

    private boolean wordToSkip(String word) {
        return skippedWords.contains(word);
    }
}

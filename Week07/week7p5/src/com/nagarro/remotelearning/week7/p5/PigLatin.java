package com.nagarro.remotelearning.week7.p5;

import java.util.StringTokenizer;

public class PigLatin {

    private static final String TO_PIG_LATIN = "ay";

    public void toPigLatin(String sentence) {
        StringTokenizer st = new StringTokenizer(sentence, " ");
        while (st.hasMoreTokens()) {
            printLatinWord(st.nextToken());
        }
    }

    private void printLatinWord(String nextToken) {

        StringBuilder pigLatinTransformedWord = new StringBuilder();
        pigLatinTransformedWord.append(nextToken.substring(1)).append(nextToken.charAt(0))
                .append(TO_PIG_LATIN);
        System.out.println(pigLatinTransformedWord);
    }
}

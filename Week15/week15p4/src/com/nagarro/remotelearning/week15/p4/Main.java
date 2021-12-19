package com.nagarro.remotelearning.week15.p4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;


public class Main {

    private static final String LATIN_INITIAL_FILE = "Latin.txt";
    private static final String CODED_UTF_FILE = "Transcoded.txt";
    private static final String UTF_FILE = "utf.txt";
    private static final String LATIN_TO_UTF_MESSAGE = "Latin to utf: ";
    private static final String UTF_TO_LATIN_MESSAGE = "Utf to latin, character that cannot be encoded: ";

    public static void main(String[] args) throws IOException {

        File initialLatinFile = new File(LATIN_INITIAL_FILE);
        File newUtfFile = new File(CODED_UTF_FILE);
        String input = FileManagement.readFile(initialLatinFile);
        String output = FileManagement.decodeText(input, "UTF-8");
        System.out.println(LATIN_TO_UTF_MESSAGE);
        System.out.println(output + "\n");
        FileManagement.writeFile(newUtfFile, output);

        File initialUtfFile = new File(UTF_FILE);
        input = FileManagement.readFile(initialUtfFile);
        System.out.println(UTF_TO_LATIN_MESSAGE);
        CharsetEncoder encoder = Charset.forName("Latin1").newEncoder();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (!encoder.canEncode(input.charAt(i))) {
                System.out.print(input.charAt(i));
                count++;
            }
        }
        System.out.println("\n" + count);
    }
}

package com.nagarro.remotelearning.week15.p4;

import java.io.*;
import java.nio.charset.Charset;

public class FileManagement {

    private static final String ERROR = "An error occurred";

    static String decodeText(String input, String encoding) {
        try {
            return
                    new BufferedReader(
                            new InputStreamReader(
                                    new ByteArrayInputStream(input.getBytes()),
                                    Charset.forName(encoding)))
                            .readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    static String readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder("");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String st;
            while ((st = br.readLine()) != null) {
                stringBuilder.append(st);
            }
            return stringBuilder.toString();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return "";
    }

    static void writeFile(File file, String output) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileWriter myWriter = new FileWriter(file);) {
                myWriter.write(output);
            } catch (IOException e) {
                System.out.println(ERROR);
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println(ERROR);
            e.printStackTrace();
        }
    }
}

package com.nagarro.remotelearning.week15.p1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Reader {

    public static String readPage(String pageURL) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL url = new URL(pageURL);
            try (Scanner scanner = new Scanner(url.openStream())) {
                while (scanner.hasNext()) {
                    stringBuffer.append(scanner.next());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

}

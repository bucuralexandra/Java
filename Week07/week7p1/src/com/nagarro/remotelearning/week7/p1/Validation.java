package com.nagarro.remotelearning.week7.p1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private final static String PATTERN_EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,3}))$";
    private final static String PATTERN_PHONE = "^(?:00|\\+|0)[0-9\\s.\\/-]{10,13}$";
    private final static String PATTERN_PASSWORD = "^.*(?=.{10,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\"#$%&'()*+,-./:;<=>[\\]?@^_`{|}~]]).*$";


    public static boolean validateEmail(String email) {

        Pattern p = Pattern.compile(PATTERN_EMAIL);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean validatePhoneNumber(String phoneNr) {

        Pattern pattern = Pattern.compile(PATTERN_PHONE);
        Matcher m = pattern.matcher(phoneNr);
        return m.matches();
    }

    public static boolean validatePassword(String password) {

        Pattern pattern = Pattern.compile(PATTERN_PASSWORD);
        Matcher m = pattern.matcher(password);
        return m.matches();
    }
}

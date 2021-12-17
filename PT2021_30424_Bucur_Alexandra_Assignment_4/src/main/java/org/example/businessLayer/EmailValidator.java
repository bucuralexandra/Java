package org.example.businessLayer;

import java.util.regex.Pattern;

/**
 * @author Alexandra
 * @since 20/05/2021
 */
public class EmailValidator {

    /**
     * a regEx pattern that validates a correct email
     */
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,3}))$";

    /**
     * @param username the client to be validated, its field email is being checked
     * @return int => true for success and false for a wrong type of input
     */
    public static boolean validate(String username) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(username).matches()) {
            return false;
        }
        return true;
    }
}

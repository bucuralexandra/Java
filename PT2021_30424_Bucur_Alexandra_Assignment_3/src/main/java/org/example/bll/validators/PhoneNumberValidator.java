package org.example.bll.validators;

import org.example.model.Client;

import java.util.regex.Pattern;

/**
 * @author Alexandra
 * @since 18/04/2021
 * Class that validates the phone number of a client
 */
public class PhoneNumberValidator implements Validator<Client> {

    /**
     * regEx that validates a phone number
     */
    private final static String PATTERN_PHONE = "^(?:00||0)[0-9\\s.\\/-]{10,13}$";

    /**
     * @param client the client to be validated, its field phoneNumber is being checked
     * @return int => 0 for success and -1 for a wrong type of input
     */
    @Override
    public int validate(Client client) {
        Pattern pattern = Pattern.compile(PATTERN_PHONE);
        if (!pattern.matcher(client.getPhoneNumber()).matches()) {
            return -1;
        }
        return 0;
    }
}

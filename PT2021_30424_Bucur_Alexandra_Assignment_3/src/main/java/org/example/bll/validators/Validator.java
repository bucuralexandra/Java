package org.example.bll.validators;

/**
 * @param <T> object type that will be checked
 * @author Alexandra
 * @see EmailValidator
 * @see PhoneNumberValidator
 * @see NumberValidator
 * @since 18/04/2021
 */
public interface Validator<T> {
    int validate(T t);
}

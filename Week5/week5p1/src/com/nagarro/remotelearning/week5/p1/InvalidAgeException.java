package com.nagarro.remotelearning.week5.p1;

public class InvalidAgeException extends RuntimeException{
    private String exceptionMessage;

    public InvalidAgeException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public java.lang.String getString() {
        return exceptionMessage;
    }

    @Override
    public String toString() {
        return "InvalidAgeException{" +
                "exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }
}

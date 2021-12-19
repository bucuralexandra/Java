package com.nagarro.remotelearning.week5.p2;

public class CustomException extends RuntimeException{

    private final String exceptionMessage;

    public CustomException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}

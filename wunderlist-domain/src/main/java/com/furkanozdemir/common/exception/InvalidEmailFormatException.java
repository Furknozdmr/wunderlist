package com.furkanozdemir.common.exception;

public class InvalidEmailFormatException extends RuntimeException {

    private static final String INVALID_EMAIL = "Invalid email format";

    public InvalidEmailFormatException() {
        super(INVALID_EMAIL);
    }
}

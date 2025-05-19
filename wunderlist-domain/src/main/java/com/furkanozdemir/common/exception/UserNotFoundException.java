package com.furkanozdemir.common.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String USER_EMAIL_NOT_FOUND_MESSAGE = "User email %s is not found";

    private static final String USER_NOT_FOUND_MESSAGE = "User is not found";

    public UserNotFoundException(String email) {
        super(String.format(USER_EMAIL_NOT_FOUND_MESSAGE, email));
    }

    public UserNotFoundException() {
        super(USER_NOT_FOUND_MESSAGE);
    }
}

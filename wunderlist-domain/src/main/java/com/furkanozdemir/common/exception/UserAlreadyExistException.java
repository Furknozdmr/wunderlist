package com.furkanozdemir.common.exception;

public class UserAlreadyExistException extends RuntimeException {

    private static final String USER_EMAIL_IS_ALREADY_MESSAGE = "User email %s is already in use";

    public UserAlreadyExistException(String email) {
        super(String.format(USER_EMAIL_IS_ALREADY_MESSAGE, email));
    }

}

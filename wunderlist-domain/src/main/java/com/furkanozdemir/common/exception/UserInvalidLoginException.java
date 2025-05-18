package com.furkanozdemir.common.exception;

public class UserInvalidLoginException extends RuntimeException {
  private static final String INVALID_USER_MESSAGE = "Invalid email or password";

  public UserInvalidLoginException() {
        super(INVALID_USER_MESSAGE);
    }
}

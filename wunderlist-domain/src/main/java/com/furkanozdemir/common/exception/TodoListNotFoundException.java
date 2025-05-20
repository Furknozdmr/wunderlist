package com.furkanozdemir.common.exception;

public class TodoListNotFoundException extends RuntimeException {

    private static final String TODO_LIST_NOT_FOUND_MESSAGE = "To-do List with id %s is not found";

    public TodoListNotFoundException(String id) {
        super(String.format(TODO_LIST_NOT_FOUND_MESSAGE, id));
    }

}

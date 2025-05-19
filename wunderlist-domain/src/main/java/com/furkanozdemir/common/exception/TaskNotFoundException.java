package com.furkanozdemir.common.exception;

public class TaskNotFoundException extends RuntimeException {

    private static final String TASK_NOT_FOUND_MESSAGE = "Task with id %s is not found";

    public TaskNotFoundException(String id) {
        super(String.format(TASK_NOT_FOUND_MESSAGE, id));
    }

}

package com.not.another.todoApp.exception;

public class TodoIsEmptyException extends RuntimeException {
    public TodoIsEmptyException(String message) {
        super(message);
    }
}

package com.not.another.todoApp.exception;

public class DatabaseError extends RuntimeException {
    public DatabaseError(String message) {
        super(message);
    }
}

package com.not.another.todoApp.exception;


//It's an unchecked exception, so you don't need to declare it with throws.
//
//Spring can propagate it to your global exception handler.
//
//You can throw it directly from your service layer.
public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(String message){
    super(message);
    }
}

package com.todo.todo_java.core.exception;


// status code 409
public class ConflictException extends Exception {
    public ConflictException() {
        super("Conflict exception");
    }

    public ConflictException(String message){
        super(message);
    }
}

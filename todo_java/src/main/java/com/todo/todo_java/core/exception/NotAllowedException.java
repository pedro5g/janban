package com.todo.todo_java.core.exception;

public class NotAllowedException extends Exception  { 
    public NotAllowedException (){
        super("Not allowed error");
    }
    public NotAllowedException(String message){
        super(message);
    }   
}

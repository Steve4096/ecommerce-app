package com.example.demo.Exceptions;

public class DuplicateException extends RuntimeException{
    public DuplicateException (String message){
        super(message);
    }
}

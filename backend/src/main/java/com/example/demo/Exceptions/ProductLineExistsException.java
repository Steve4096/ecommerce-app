package com.example.demo.Exceptions;

public class ProductLineExistsException extends RuntimeException{
    public ProductLineExistsException(String message){
        super(message);
    }
}

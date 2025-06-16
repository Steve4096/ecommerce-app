package com.example.demo.Exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long customerNumber){
        super("Customer with id number "+customerNumber+" not found");
    }

    public CustomerNotFoundException(String firstName){
        super("Customer with first name: "+firstName+" not found");
    }
}

package com.example.trainbooking.exception;

public class EmptyInputException extends RuntimeException{

    public EmptyInputException(String message)
    {
        super(message);
    }
}

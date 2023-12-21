package com.example.trainbooking.exception;

public class NoRecordFoundException extends RuntimeException{

    public NoRecordFoundException(String message)
    {
        super(message);
    }
}

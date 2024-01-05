package com.trainbooking.trainmicroservices.exception;

public class TrainNumberNotExistException extends Exception{
    public TrainNumberNotExistException(String message) {
        super(message);
    }
}

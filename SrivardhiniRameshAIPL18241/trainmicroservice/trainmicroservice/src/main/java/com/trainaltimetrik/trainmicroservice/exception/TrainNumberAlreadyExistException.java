package com.trainaltimetrik.trainmicroservice.exception;

public class TrainNumberAlreadyExistException extends Exception{
    String message;
    public TrainNumberAlreadyExistException(String errorGettingTrainByNumber, Exception e) {
        super();
    }
    public TrainNumberAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}

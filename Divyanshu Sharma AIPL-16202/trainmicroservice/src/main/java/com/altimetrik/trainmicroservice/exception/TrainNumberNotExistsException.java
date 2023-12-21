package com.altimetrik.trainmicroservice.exception;

public class TrainNumberNotExistsException extends Exception {

    String message;
    public TrainNumberNotExistsException()
    {
        super();
    }

    public TrainNumberNotExistsException(String message) {
        super(message);
        this.message = message;
    }

}

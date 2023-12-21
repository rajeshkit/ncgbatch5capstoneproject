package com.altimetrik.trainschedule.exception;

public class TrainNumberNotFoundException extends Exception{
    String message;

    public TrainNumberNotFoundException() {
    }

    public TrainNumberNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}

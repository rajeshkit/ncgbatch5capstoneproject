package com.altimetrik.trainmicroservice.exception;

public class TrainNumberAlreadyExistsException extends Exception {

    String msg;

    public TrainNumberAlreadyExistsException() {
        super();
    }

    public TrainNumberAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

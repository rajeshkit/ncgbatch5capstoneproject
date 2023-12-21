package com.altimetrik.trainmicroservices.exception;

public class TrainNumberAlreadyExistsException extends Exception {
    String msg;

    public TrainNumberAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public TrainNumberAlreadyExistsException() {
        super();
    }

}

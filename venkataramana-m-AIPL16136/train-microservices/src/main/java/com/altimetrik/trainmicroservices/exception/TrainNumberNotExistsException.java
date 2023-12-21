package com.altimetrik.trainmicroservices.exception;

public class TrainNumberNotExistsException extends Exception {
    String msg;

    public TrainNumberNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public TrainNumberNotExistsException() {
        super();
    }
}







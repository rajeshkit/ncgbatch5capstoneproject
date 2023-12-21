package com.altimetrik.trainmicroservice.exception;

public class TrainNumberNotExistsException extends Exception{

    String msg;

    public TrainNumberNotExistsException() {
        super();
    }

    public TrainNumberNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

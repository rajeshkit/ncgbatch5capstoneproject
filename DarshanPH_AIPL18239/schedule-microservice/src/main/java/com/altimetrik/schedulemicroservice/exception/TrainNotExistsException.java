package com.altimetrik.schedulemicroservice.exception;

public class TrainNotExistsException extends Exception{
    String msg;

    public TrainNotExistsException() {
    }

    public TrainNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

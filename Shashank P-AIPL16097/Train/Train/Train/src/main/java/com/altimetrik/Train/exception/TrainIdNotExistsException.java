package com.altimetrik.Train.exception;

public class TrainIdNotExistsException extends Exception{
    String msg;

    public TrainIdNotExistsException() {
    }

    public TrainIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

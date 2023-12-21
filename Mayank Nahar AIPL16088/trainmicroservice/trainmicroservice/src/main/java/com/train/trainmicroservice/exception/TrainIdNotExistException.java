package com.train.trainmicroservice.exception;

public class TrainIdNotExistException extends Exception {
    String msg;

    public TrainIdNotExistException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public TrainIdNotExistException() {
        super();
    }
}

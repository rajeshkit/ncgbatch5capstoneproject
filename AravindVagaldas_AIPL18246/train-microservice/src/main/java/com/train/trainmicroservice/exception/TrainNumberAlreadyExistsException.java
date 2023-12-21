package com.train.trainmicroservice.exception;

public class TrainNumberAlreadyExistsException extends RuntimeException{
    String msg;

    public TrainNumberAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

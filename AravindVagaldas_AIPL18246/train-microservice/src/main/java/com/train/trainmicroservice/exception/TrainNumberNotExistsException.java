package com.train.trainmicroservice.exception;

public class TrainNumberNotExistsException extends RuntimeException {
    final String msg;


    public TrainNumberNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

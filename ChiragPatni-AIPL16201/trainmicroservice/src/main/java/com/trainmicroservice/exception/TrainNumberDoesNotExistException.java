package com.trainmicroservice.exception;

public class TrainNumberDoesNotExistException extends Exception{
    final String msg;
    public TrainNumberDoesNotExistException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

package com.altimetrik.train.exception;

public class TrainNumberNotFoundException extends RuntimeException {
    final String msg;

    public TrainNumberNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

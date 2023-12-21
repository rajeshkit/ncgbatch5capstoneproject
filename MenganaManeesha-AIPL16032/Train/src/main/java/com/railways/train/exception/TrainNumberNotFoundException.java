package com.railways.train.exception;

public class TrainNumberNotFoundException extends Exception{
    final String msg;
    public TrainNumberNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }

}

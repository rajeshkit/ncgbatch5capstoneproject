package com.railways.train.exception;

public class TrainWithThatNumberExists extends Exception {
    final String msg;
    public TrainWithThatNumberExists(String msg) {
        super(msg);
        this.msg=msg;
    }
}

package com.schedulemicroservice.exception;

public class TrainNotFoundException extends Exception {
    final String msg;
    public TrainNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

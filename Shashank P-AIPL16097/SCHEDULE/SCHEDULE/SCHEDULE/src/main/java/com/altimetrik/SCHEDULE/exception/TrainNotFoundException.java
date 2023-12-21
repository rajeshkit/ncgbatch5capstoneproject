package com.altimetrik.SCHEDULE.exception;

public class TrainNotFoundException extends Exception {
    String msg;
    public TrainNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

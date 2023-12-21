package com.altimetrik.schedule.exception;

public class TrainNotExistsException extends Throwable {
    String msg;

    public TrainNotExistsException() {
        super();
    }

    public TrainNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

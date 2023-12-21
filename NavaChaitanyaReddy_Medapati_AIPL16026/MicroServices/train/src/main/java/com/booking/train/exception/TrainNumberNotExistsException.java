package com.booking.train.exception;

public class TrainNumberNotExistsException extends Exception{
    String msg;
    public TrainNumberNotExistsException()
    {
        super();
    }

    public TrainNumberNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

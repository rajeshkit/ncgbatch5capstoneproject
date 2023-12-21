package com.altimetrik.train.exception;

public class TrainAlreadyExistsException extends Exception{
    String msg;

    public TrainAlreadyExistsException() {
        super();
    }

    public TrainAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

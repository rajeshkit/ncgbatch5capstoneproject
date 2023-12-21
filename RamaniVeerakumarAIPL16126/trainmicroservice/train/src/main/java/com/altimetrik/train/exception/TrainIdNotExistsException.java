package com.altimetrik.train.exception;

public class TrainIdNotExistsException extends Exception {
    String msg;
    public TrainIdNotExistsException(){
        super();
    }
    public TrainIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

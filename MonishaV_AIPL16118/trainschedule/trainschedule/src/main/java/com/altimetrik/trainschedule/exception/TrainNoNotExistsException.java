package com.altimetrik.trainschedule.exception;

public class TrainNoNotExistsException extends Exception {
    String msg;
    public TrainNoNotExistsException(){
        super();
    }
    public TrainNoNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

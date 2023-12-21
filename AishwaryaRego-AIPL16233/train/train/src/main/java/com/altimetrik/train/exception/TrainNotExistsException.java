package com.altimetrik.train.exception;
public class TrainNotExistsException extends Exception{
    String msg;
    public TrainNotExistsException(){
        super();
    }
    public TrainNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

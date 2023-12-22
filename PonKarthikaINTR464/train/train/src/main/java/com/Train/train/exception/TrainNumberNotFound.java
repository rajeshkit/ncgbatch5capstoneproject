package com.Train.train.exception;

public class TrainNumberNotFound extends Exception{
    String msg;
    public TrainNumberNotFound(){
        super();
    }
    public TrainNumberNotFound(String msg) {
        super(msg);
        this.msg = msg;
    }
}

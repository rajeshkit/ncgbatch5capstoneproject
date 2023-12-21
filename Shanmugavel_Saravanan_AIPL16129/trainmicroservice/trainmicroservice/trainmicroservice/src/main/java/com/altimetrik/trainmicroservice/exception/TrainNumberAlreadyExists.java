package com.altimetrik.trainmicroservice.exception;

public class TrainNumberAlreadyExists extends Exception
{

    String msg;
    public TrainNumberAlreadyExists(){
        super();
    }
    public TrainNumberAlreadyExists(String msg) {
        super(msg);
        this.msg = msg;

    }
}

package com.altimetrik.trainmicroservice.exception;

public class TrainNumberNotFoundException extends Exception
{
    String msg;
    public TrainNumberNotFoundException(){
        super();
    }
    public TrainNumberNotFoundException(String msg) {
        super(msg);
        this.msg = msg;

    }
}

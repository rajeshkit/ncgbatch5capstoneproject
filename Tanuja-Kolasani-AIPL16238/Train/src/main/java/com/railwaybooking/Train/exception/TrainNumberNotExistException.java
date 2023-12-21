package com.railwaybooking.Train.exception;


public class TrainNumberNotExistException extends Exception{
    String msg;
    public TrainNumberNotExistException(){
        super();
    }
    public TrainNumberNotExistException(String msg){
        super(msg);
        this.msg=msg;
    }
}

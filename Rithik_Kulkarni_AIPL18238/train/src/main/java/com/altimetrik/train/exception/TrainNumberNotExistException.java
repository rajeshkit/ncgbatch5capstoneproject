package com.altimetrik.train.exception;

public class TrainNumberNotExistException extends Exception{

    private String message;

    public TrainNumberNotExistException(){

    }

    public TrainNumberNotExistException(String message){
        super(message);
    }

}

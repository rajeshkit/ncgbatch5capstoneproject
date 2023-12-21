package com.finalproject.railwaysmicroservice.exception;

public class TrainIsNotExistHereException extends Exception{

    String msg ;

    public TrainIsNotExistHereException(String message, String msg) {
        super(message);
        this.msg = msg;
    }


}

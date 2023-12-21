package com.trainaltimetrik.trainmicroservice.exception;

public class TrainNumberNotExistException extends Exception {
    String msg;

    public TrainNumberNotExistException(String errorGettingTrainByNumber, Exception e) {
        super();
    }

    public TrainNumberNotExistException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
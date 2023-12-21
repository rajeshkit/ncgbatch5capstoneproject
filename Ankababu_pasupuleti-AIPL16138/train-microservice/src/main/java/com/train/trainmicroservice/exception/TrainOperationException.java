package com.train.trainmicroservice.exception;

public class TrainOperationException extends RuntimeException{
    //this exception is used for the operations of train can not be completed
    public TrainOperationException(String message){
        super(message);
    }
}

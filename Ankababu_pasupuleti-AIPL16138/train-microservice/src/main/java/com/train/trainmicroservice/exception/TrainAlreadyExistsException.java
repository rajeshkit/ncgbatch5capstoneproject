package com.train.trainmicroservice.exception;

public class TrainAlreadyExistsException extends RuntimeException{
    public TrainAlreadyExistsException(String message){
        super(message);
    }

}

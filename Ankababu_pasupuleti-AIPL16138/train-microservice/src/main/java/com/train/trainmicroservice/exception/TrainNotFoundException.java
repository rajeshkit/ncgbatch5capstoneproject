package com.train.trainmicroservice.exception;

public class TrainNotFoundException extends RuntimeException{
    public TrainNotFoundException(String message){
        super(message);
    }
}

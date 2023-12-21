package com.booking_details.train.exception;

public class TrainIdNotFoundException extends Exception{

    public TrainIdNotFoundException() {
        super("Please check the train number...");
    }
}

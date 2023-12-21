package com.TrainBookingSystem.Train.Exception;

public class TrainIdNotFoundException extends Exception{
    String msg;
    public TrainIdNotFoundException() {
        super();
    }

    public TrainIdNotFoundException(String message) {
        super(message);
        this.msg=message;
    }
}

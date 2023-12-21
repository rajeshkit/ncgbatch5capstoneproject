package com.Booking.train.customexception;

public class TrainIdNotFoundException extends Exception {
String msg;
    public TrainIdNotFoundException() {
        super();
    }
    public TrainIdNotFoundException(String msg) {
    super(msg);
        this.msg = msg;
    }
}

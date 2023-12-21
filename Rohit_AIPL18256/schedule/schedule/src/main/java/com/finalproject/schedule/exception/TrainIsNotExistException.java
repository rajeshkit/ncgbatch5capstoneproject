package com.finalproject.schedule.exception;

public class TrainIsNotExistException extends Exception {

    String mesg ;

    public TrainIsNotExistException(String mesg) {
        this.mesg = mesg;
    }

    public String getLocalizedMessage() {
        return null;
    }
}

package com.altimetrikfinalproject.schedule.exception;

public class TrainIdDoesNotExistException extends Exception{
    private String msg;

    public TrainIdDoesNotExistException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}

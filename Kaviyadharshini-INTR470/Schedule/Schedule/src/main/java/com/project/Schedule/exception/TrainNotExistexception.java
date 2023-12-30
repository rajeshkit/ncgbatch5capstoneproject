package com.project.Schedule.exception;

public class TrainNotExistexception extends Exception {

    String msg ;

    public void TrainNotExistException(String msg) {
        this.msg = msg;
    }

    public String getLocalizedMessage() {
        return null;
    }
}


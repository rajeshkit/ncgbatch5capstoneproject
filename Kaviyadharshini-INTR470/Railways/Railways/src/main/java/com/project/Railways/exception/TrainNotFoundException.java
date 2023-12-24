package com.project.Railways.exception;

public class TrainNotFoundException extends Exception{

    String msg ;

    public TrainNotFoundException(String message, String msg) {
        super(message);
        this.msg = msg;
    }


}

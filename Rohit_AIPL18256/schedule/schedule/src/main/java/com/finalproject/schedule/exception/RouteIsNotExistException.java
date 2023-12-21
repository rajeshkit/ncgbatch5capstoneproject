package com.finalproject.schedule.exception;

public class RouteIsNotExistException extends Exception {

    private String mesg ;

    public RouteIsNotExistException(String mesg) {
        this.mesg = mesg;
    }

    public String getLocalizedMessage() {
        return null ;
    }
}

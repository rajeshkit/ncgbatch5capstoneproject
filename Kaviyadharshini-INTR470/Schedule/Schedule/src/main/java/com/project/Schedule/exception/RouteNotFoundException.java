package com.project.Schedule.exception;

public class RouteNotFoundException extends Exception {

    private String mesg ;

    public RouteNotFoundException(String mesg) {
        this.mesg = mesg;
    }

    public String getLocalizedMessage() {
        return null ;
    }
}

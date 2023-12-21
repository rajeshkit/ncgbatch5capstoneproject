package com.altimetrik.trainschedule.exception;

public class RouteIdNotFoundException extends Exception{
    String message;

    public RouteIdNotFoundException () {
    }

    public RouteIdNotFoundException (String message) {
        super(message);
        this.message = message;
    }
}

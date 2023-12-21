package com.altimetrik.SCHEDULE.exception;

public class RouteNotFoundException extends Exception {
    String msg;
    public RouteNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

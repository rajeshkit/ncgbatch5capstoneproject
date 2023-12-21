package com.schedulemicroservice.exception;

public class RouteNotFoundException extends Exception {
    final String msg;
    public RouteNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

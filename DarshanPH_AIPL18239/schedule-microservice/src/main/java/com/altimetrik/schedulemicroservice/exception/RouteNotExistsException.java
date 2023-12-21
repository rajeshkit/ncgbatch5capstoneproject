package com.altimetrik.schedulemicroservice.exception;

public class RouteNotExistsException extends Exception{
    String msg;

    public RouteNotExistsException() {
    }

    public RouteNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

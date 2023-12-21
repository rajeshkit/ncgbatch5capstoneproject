package com.altimetrik.routemicroservice.exception;

public class RouteIdNotExistsException extends Exception{
    String msg;

    public RouteIdNotExistsException() {
    }

    public RouteIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

package com.altimetrik.routemicroservice.exception;

public class RouteIdAlreadyExistsException extends Exception {
    String msg;

    public RouteIdAlreadyExistsException() {
        super();
    }

    public RouteIdAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

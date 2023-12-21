package com.altimetrik.routemicroservices.exception;

public class RouteIdAlreadyExistsException extends Exception {
    String msg;

    public RouteIdAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RouteIdAlreadyExistsException() {
        super();
    }
}

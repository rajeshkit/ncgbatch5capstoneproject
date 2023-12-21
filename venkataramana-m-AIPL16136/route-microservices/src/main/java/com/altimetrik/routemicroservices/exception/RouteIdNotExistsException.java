package com.altimetrik.routemicroservices.exception;

public class RouteIdNotExistsException extends Exception {
    String msg;

    public RouteIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RouteIdNotExistsException() {
        super();
    }
}

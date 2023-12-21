package com.altimetrik.routemicroservice.exception;

public class RouteIdNotExistException extends Exception {
    String message;

    public RouteIdNotExistException() {
        super();
    }

    public RouteIdNotExistException(String message) {
        super(message);
        this.message = message;
    }
}

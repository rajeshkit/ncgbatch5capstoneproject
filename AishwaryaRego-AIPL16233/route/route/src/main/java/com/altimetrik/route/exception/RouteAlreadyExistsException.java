package com.altimetrik.route.exception;

public class RouteAlreadyExistsException extends Exception{
    String msg;

    public RouteAlreadyExistsException() {
        super();
    }

    public RouteAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

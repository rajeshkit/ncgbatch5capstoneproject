package com.booking.route.exception;

public class RouteIdNotExistsException extends Exception{
    String msg;
    public RouteIdNotExistsException()
    {
        super();
    }

    public RouteIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

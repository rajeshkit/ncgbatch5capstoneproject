package com.railways.route.exception;

public class RouteIdExistsException extends Throwable {
    final String msg;
    public RouteIdExistsException(String msg) {
        super(msg);
        this.msg=msg;
    }
}

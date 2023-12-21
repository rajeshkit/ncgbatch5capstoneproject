package com.altimetrik.schedule.exception;

public class RouteNotExistsException extends Throwable {
    String msg;

    public RouteNotExistsException() {
        super();
    }

    public RouteNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
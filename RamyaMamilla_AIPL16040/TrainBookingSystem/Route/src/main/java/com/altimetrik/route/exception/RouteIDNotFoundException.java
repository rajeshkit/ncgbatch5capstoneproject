package com.altimetrik.route.exception;

public class RouteIDNotFoundException extends RuntimeException{
     final String msg;

    public RouteIDNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

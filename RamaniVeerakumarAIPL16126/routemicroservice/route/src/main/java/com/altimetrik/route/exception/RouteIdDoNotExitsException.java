package com.altimetrik.route.exception;

public class RouteIdDoNotExitsException extends Exception {
    String msg;
    public  RouteIdDoNotExitsException(){super();
    }
    public  RouteIdDoNotExitsException(String msg) {
        super(msg);
        this.msg = msg;
}
}

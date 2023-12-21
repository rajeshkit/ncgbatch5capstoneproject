package com.altimetrik.routemicroservice.exception;

public class RouteNotFoundException extends Exception{
    String msg;
    public RouteNotFoundException(){
        super();
    }
    public RouteNotFoundException(String msg) {
        super(msg);
        this.msg = msg;

    }
}

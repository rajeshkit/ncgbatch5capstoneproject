package com.route.routemicroservice.exception;

public class RouteIdAlreadyExistException extends RuntimeException{
    String msg;

    public RouteIdAlreadyExistException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

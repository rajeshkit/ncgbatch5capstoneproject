package com.route.routemicroservice.exception;

public class RouteIdNotExistException extends RuntimeException{
    final String msg;


    public RouteIdNotExistException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

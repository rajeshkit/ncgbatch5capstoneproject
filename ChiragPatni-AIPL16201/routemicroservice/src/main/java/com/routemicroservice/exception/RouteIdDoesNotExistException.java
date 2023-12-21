package com.routemicroservice.exception;

public class RouteIdDoesNotExistException extends Exception{
    final String msg;
    public RouteIdDoesNotExistException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
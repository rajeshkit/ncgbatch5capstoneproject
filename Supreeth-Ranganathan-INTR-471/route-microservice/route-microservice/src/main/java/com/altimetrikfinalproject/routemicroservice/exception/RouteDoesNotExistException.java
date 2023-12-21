package com.altimetrikfinalproject.routemicroservice.exception;

public class RouteDoesNotExistException extends Exception{
    String msg;

    public RouteDoesNotExistException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}

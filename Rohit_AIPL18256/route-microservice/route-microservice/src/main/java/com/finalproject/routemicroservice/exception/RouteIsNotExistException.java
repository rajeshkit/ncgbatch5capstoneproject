package com.finalproject.routemicroservice.exception;

public class RouteIsNotExistException extends Exception{

    String msg ;

    public RouteIsNotExistException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}

package com.altimetrik.route.exception;

public class RouteIdNotExistException extends Exception{

    private String message;

    public RouteIdNotExistException(){

    }

    public RouteIdNotExistException(String message){
        super(message);
    }
}

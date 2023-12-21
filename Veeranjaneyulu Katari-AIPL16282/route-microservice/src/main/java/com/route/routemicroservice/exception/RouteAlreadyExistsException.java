package com.route.routemicroservice.exception;

public class RouteAlreadyExistsException extends RuntimeException{
    public RouteAlreadyExistsException(String message){
        super(message);
    }
}

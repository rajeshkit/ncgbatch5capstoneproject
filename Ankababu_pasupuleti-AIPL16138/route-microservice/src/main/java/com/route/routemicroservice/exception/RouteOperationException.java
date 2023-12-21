package com.route.routemicroservice.exception;

public class RouteOperationException extends RuntimeException{
    public RouteOperationException(String message){
        super(message);
    }
}

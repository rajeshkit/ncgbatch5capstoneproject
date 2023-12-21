package com.route.routemicroservice.exception;

public class RouteNotFoundException extends RuntimeException{
    public RouteNotFoundException(String message){
        super(message);
    }
}

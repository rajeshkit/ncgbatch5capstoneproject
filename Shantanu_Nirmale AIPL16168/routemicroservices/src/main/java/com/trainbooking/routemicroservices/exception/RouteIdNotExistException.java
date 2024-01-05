package com.trainbooking.routemicroservices.exception;

public class RouteIdNotExistException extends Exception{

    public RouteIdNotExistException(String message) {
       super(message);
    }
}

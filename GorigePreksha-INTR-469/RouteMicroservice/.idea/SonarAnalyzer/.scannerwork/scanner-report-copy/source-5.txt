package com.Route.RouteMicroservice.exceptionhandling;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(String message) {
        super(message);
    }
}


package com.RouteMicroservices.Route.Exception;

public class RouteIdNotFoundException extends Exception{
    String msg;
    public RouteIdNotFoundException() {
        super();
    }

    public RouteIdNotFoundException(String message) {
        super(message);
        this.msg=message;
    }
}

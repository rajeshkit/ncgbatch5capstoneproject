package com.project.routeService.exception;

public class RouteNotFoundException extends Exception{

    String msg ;

    public RouteNotFoundException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}

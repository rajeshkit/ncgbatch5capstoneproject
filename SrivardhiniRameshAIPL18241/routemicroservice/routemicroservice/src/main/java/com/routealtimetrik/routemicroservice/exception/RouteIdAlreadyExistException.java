package com.routealtimetrik.routemicroservice.exception;

public class RouteIdAlreadyExistException extends Exception {
    String msg;

    public RouteIdAlreadyExistException(String errorDeletingRouteById, Exception e) {
        super();
    }

    public RouteIdAlreadyExistException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

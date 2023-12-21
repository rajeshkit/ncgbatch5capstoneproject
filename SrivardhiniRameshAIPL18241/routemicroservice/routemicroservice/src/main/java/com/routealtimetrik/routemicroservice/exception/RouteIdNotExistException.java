package com.routealtimetrik.routemicroservice.exception;

public class RouteIdNotExistException extends Exception {
    String msg;

    public RouteIdNotExistException(String errorDeletingRouteById, Exception e) {
        super();
    }

    public RouteIdNotExistException(String msg) {
        super(msg);
        this.msg = msg;
    }

}

package com.Route.route.exception;

public class RouteIdNotFoundException extends Exception{
    String msg;
    public RouteIdNotFoundException(){
        super();
    }
    public RouteIdNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

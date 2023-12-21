package com.railways.route.exceptions;

public class RouteIdNotFound extends  Exception{
    String msg;
    public RouteIdNotFound() {
        super();
    }

    public RouteIdNotFound(String message) {
        super(message);
        this.msg=message;
    }
}

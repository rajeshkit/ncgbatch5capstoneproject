package com.route.routemicroservice.exception;

public class RouteIdNotFoundException extends Exception {
    String s;

    public RouteIdNotFoundException() {super();
    }

    public RouteIdNotFoundException(String s) {
        super(s);
        this.s = s;
    }
}

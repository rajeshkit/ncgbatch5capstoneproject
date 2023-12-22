package com.altimetrik.route.exception;

public class RouteNotExistsException extends Exception {
    String mgs;

    public RouteNotExistsException() {
        super();
    }

    public RouteNotExistsException(String mgs) {
        super(mgs);
        this.mgs = mgs;
    }
}

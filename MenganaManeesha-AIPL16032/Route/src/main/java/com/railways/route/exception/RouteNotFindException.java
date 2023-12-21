package com.railways.route.exception;

public class RouteNotFindException extends Exception {
    final String msg;
    public RouteNotFindException(String msg) {
        super(msg);
        this.msg=msg;
    }
}

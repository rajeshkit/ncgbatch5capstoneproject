package com.altimetrik.route.exception;

public class RouteNotExistsException extends Exception {
    String msg;
    public RouteNotExistsException(){ super();}
    public RouteNotExistsException(String msg){
        super(msg);
        this.msg = msg;
    }

}

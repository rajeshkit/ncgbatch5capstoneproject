package com.altimetrik.trainschedule.exception;

public class RouteIdNotExistsException extends Exception  {
    String msg;
    public RouteIdNotExistsException(){
        super();
    }
    public RouteIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }

}

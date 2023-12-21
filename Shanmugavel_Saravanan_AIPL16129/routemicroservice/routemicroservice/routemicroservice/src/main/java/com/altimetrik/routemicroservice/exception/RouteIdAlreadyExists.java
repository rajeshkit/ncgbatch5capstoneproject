package com.altimetrik.routemicroservice.exception;

public class RouteIdAlreadyExists extends Exception
{

    String msg;
    public RouteIdAlreadyExists(){
        super();
    }
    public RouteIdAlreadyExists(String msg) {
        super(msg);
        this.msg = msg;

    }
}

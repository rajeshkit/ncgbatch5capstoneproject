package com.altimetrikfinalproject.schedule.exception;

public class RouteIdDoesNotExistException extends Exception{
    private String msg;

    public RouteIdDoesNotExistException(String msg) {
        this.msg = msg;
    }
}

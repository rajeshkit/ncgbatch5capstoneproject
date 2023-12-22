package com.altimetrik.schedule.exception;

public class InvalidRouteException extends Throwable {
    String mgs;

    public InvalidRouteException() {
        super();
    }

    public InvalidRouteException(String mgs) {
        super(mgs);
        this.mgs = mgs;
    }
}

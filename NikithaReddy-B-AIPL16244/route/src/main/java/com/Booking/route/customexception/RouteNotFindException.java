package com.Booking.route.customexception;

public class RouteNotFindException extends Throwable {
    String msg;

    public RouteNotFindException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

package com.altimetrik.schedulemicroservice.exception;

public class BookingNotFoundException extends RuntimeException {
    String msg;
    public BookingNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BookingNotFoundException() {
        super();
    }


}

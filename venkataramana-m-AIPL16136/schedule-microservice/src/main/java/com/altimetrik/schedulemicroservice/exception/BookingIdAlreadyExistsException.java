package com.altimetrik.schedulemicroservice.exception;

public class BookingIdAlreadyExistsException extends Exception{
    String msg;
    public BookingIdAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public BookingIdAlreadyExistsException() {
        super();
    }
}

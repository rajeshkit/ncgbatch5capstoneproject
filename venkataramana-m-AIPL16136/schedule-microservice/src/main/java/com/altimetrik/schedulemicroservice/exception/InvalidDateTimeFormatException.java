package com.altimetrik.schedulemicroservice.exception;

public class InvalidDateTimeFormatException extends RuntimeException {
    String msg;
    public InvalidDateTimeFormatException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public InvalidDateTimeFormatException() {
        super();
    }


}
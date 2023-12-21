package com.schedule.schedulemicroservice.exception;

public class InvalidDateTimeException extends RuntimeException {
    public InvalidDateTimeException(String s) {
        super(s);
    }
}

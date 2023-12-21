package com.schedulemicroservice.exception;

public class ScheduleIdDoesNotExistException extends Exception {
    final String msg;
    public ScheduleIdDoesNotExistException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

package com.altimetrik.schedulemicroservice.exception;

public class ScheduleIdAlreadyExistsException extends Exception {
    String msg;
    public ScheduleIdAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public ScheduleIdAlreadyExistsException() {
        super();
    }
}

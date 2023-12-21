package com.altimetrik.schedulemicroservice.exception;

public class ScheduleIdNotExistsException extends Exception{
    String msg;
    public ScheduleIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public ScheduleIdNotExistsException() {
        super();
    }
}

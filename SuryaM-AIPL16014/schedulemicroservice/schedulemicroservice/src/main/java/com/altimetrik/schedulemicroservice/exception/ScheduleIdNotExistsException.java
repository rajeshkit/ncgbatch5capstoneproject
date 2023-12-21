package com.altimetrik.schedulemicroservice.exception;

public class ScheduleIdNotExistsException extends Exception{
    String msg;

    public ScheduleIdNotExistsException() {
        super();
    }

    public ScheduleIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

package com.schedule.schedulemicroservice.exception;

public class ScheduleIdNotExistsException extends RuntimeException{
    final String msg;

    public ScheduleIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

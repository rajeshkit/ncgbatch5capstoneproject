package com.altimetrik.SCHEDULE.exception;

public class ScheduleIdNotExistsException extends Exception {
    String msg;
    public ScheduleIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

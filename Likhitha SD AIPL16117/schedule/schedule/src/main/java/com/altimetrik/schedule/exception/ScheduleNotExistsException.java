package com.altimetrik.schedule.exception;

public class ScheduleNotExistsException extends Exception {
    String msg;
    public ScheduleNotExistsException(){
        super();
    }
    public ScheduleNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
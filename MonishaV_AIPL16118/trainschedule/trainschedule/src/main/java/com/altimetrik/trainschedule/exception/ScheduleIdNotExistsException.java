package com.altimetrik.trainschedule.exception;

public class ScheduleIdNotExistsException extends Exception {
    String msg;
    public ScheduleIdNotExistsException(){
        super();
    }
    public ScheduleIdNotExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

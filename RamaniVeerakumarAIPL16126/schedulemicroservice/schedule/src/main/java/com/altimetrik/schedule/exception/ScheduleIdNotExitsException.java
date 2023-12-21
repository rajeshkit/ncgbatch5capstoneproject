package com.altimetrik.schedule.exception;

public class ScheduleIdNotExitsException extends Exception{
    String msg;
    public ScheduleIdNotExitsException(){
        super();
    }
    public ScheduleIdNotExitsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

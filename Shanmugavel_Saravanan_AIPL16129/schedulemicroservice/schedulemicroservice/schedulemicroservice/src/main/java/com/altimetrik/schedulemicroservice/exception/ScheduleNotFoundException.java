package com.altimetrik.schedulemicroservice.exception;

public class ScheduleNotFoundException extends Exception{
    String msg;
    public ScheduleNotFoundException(){
        super();
    }
    public ScheduleNotFoundException(String msg) {
        super(msg);
        this.msg = msg;

    }
}

package com.railwaybooking.Schedule.exception;

public class ScheduleNotFoundException extends Exception{
    String msg;
    public ScheduleNotFoundException(String msg){
        super(msg);
        this.msg=msg;
    }
}

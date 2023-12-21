package com.altimetrik.schedule.exception;

public class ScheduleIDNotFoundException extends Exception{
    final String msg;

    public ScheduleIDNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

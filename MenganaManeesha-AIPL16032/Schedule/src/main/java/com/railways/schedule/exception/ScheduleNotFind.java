package com.railways.schedule.exception;

public class ScheduleNotFind extends Exception {
    final String msg;
    public ScheduleNotFind(String msg) {
        super(msg);
        this.msg=msg;
    }
}

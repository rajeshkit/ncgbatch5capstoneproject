package com.schedulealtimetrik.schedulemicroservice.exception;

public class ScheduleIdNotExistException extends Exception{
    String msg;

    public ScheduleIdNotExistException(String errorDeletingScheduleById, Exception e) {
        super();
    }

    public ScheduleIdNotExistException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

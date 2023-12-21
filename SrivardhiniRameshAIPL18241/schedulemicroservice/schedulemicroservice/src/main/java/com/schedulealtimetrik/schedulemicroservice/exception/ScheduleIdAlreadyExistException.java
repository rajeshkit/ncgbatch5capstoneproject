package com.schedulealtimetrik.schedulemicroservice.exception;

public class ScheduleIdAlreadyExistException extends Exception {
    String msg;

    public ScheduleIdAlreadyExistException(String errorDeletingScheduleById, Exception e) {
        super();
    }

    public ScheduleIdAlreadyExistException(String msg) {
        super(msg);
        this.msg = msg;
    }
}

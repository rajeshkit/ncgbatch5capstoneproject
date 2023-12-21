package com.altimetrik.schedule.exception;

public class ScheduleIdNotExistException extends Exception{

    private String message;

    public ScheduleIdNotExistException() {
    }

    public ScheduleIdNotExistException(String message) {
        super(message);
    }
}

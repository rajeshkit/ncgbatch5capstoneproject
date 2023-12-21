package com.altimetrik.trainschedule.exception;

public class ScheduleIdNotFoundException extends Exception{
    String message;

    public ScheduleIdNotFoundException() {
    }

    public ScheduleIdNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}

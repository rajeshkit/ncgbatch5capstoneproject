package com.ScheduleMicroservices.Schedule.exception;

public class ScheduleIdNotFoundException extends Exception{
    String msg;
    public ScheduleIdNotFoundException() {
        super();
    }

    public ScheduleIdNotFoundException(String message) {
        super(message);
        this.msg=message;
    }
}

package com.schedule.schedulemicroservice.exception;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(String message){
        super(message);
    }
}

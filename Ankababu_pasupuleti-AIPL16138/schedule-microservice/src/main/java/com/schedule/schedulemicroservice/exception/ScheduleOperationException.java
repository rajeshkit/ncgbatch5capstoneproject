package com.schedule.schedulemicroservice.exception;

public class ScheduleOperationException extends RuntimeException{
    public ScheduleOperationException(String message){
        super(message);
    }
}

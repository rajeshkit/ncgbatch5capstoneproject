package com.schedule.schedulemicroservice.exception;

public class OverlappingScheduleException extends RuntimeException{
    public OverlappingScheduleException(String message){
        super(message);
    }
}

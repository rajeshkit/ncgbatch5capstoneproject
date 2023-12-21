package com.schedule.schedulemicroservice.exception;

public class InvalidScheduleRequestException extends RuntimeException{
    public InvalidScheduleRequestException(String message){
        super(message);
    }
}

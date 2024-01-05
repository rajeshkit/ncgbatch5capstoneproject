package com.trainbooking.schedulemicroservices.exception;

public class ScheduleIdNotExistException extends  Exception{

    public ScheduleIdNotExistException(String message) {
        super(message);
    }
}

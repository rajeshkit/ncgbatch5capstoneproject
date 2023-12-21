package com.altimetrik.schedulemicroservice.exceptions;


import lombok.Getter;

@Getter
public class ScheduleIdNotExistsException extends RuntimeException {

    private final String errorMessage;

    public ScheduleIdNotExistsException(String errorMessage) {
        super(errorMessage + " From `TrainIdNotExistsException`");
        this.errorMessage = errorMessage;
    }

}
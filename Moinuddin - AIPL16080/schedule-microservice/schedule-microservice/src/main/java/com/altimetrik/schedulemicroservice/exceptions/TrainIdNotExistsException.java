package com.altimetrik.schedulemicroservice.exceptions;


import lombok.Getter;

@Getter
public class TrainIdNotExistsException extends Exception {

    private final String errorMessage;

    public TrainIdNotExistsException(String errorMessage) {
        super(errorMessage + " From `TrainIdNotExistsException`");
        this.errorMessage = errorMessage;
    }

}
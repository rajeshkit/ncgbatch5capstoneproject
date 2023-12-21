package com.example.trainbooking.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class TrainIdNotFoundException extends RuntimeException{
    private final int status;

    public TrainIdNotFoundException(String message, int status) {
        super(message);
        this.status = status;
    }

}
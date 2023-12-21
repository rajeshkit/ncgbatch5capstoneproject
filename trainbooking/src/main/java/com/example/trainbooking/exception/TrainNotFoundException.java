package com.example.trainbooking.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
public class TrainNotFoundException extends RuntimeException{

    private final int status;

    public TrainNotFoundException(String message, int status) {
        super(message);
        this.status = status;
    }
    }

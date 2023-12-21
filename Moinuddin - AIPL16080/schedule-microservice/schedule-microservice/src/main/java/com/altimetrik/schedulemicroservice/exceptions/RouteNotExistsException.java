package com.altimetrik.schedulemicroservice.exceptions;

import lombok.Getter;

@Getter
public class RouteNotExistsException extends Exception {

    private final String errorMessage;

    public RouteNotExistsException(String errorMessage) {
        super(errorMessage + " From `RouteNotExistsException`");
        this.errorMessage = errorMessage;
    }

}
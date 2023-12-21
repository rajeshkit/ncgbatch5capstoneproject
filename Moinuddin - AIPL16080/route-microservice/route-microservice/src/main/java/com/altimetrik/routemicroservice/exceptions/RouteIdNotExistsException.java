package com.altimetrik.routemicroservice.exceptions;

import lombok.Getter;
@Getter
public class RouteIdNotExistsException extends Exception {

    private final String errorMessage;

    public RouteIdNotExistsException(String errorMessage) {
        super(errorMessage + " From `RouteIdNotExistsException`");
        this.errorMessage = errorMessage+" From `RouteIdNotExistsException`";
    }

}
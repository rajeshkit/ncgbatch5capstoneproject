package com.altimetrik.Route.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationConfiguration {

    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

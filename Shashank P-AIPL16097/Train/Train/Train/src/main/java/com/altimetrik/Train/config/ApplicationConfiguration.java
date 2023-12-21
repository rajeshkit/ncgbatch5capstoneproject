package com.altimetrik.Train.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfiguration {
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}

package com.altimetrik.schedule.config;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
@Configuration
public class ApplicationConfiguration {
    @Bean
    public ObjectMapper objectMapper(){

        return new ObjectMapper();
    }
}

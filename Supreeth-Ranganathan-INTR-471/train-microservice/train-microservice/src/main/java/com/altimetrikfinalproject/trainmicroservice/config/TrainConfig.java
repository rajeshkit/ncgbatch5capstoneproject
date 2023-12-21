package com.altimetrikfinalproject.trainmicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainConfig {
    @Bean
    public org.modelmapper.ModelMapper modelMapperBean() {
        return new org.modelmapper.ModelMapper();
    }
}

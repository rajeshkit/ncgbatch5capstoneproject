package com.altimetrikfinalproject.routemicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RouteConfig {
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }
}

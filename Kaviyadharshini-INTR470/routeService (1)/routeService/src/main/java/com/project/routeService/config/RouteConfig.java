package com.project.routeService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RouteConfig {

    @Bean
    public RestTemplate restTemplateBean(){
        return new RestTemplate();
    }
}


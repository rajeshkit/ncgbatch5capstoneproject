package com.altimetrikfinalproject.trainmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.altimetrikfinalproject.trainmicroservice")
public class TrainMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainMicroserviceApplication.class, args);
	}

}

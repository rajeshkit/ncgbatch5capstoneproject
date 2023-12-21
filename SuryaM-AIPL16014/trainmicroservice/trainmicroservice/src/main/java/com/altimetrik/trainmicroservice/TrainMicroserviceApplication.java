package com.altimetrik.trainmicroservice;

import com.altimetrik.trainmicroservice.service.TrainServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TrainMicroserviceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TrainMicroserviceApplication.class, args);
		TrainServiceImpl trainServiceImpl = context.getBean(TrainServiceImpl.class);
		trainServiceImpl.printBaseUrl();
	}
}
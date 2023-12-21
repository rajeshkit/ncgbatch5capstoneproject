package com.schedule.schedulemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = {"com.train.trainmicroservice.model","com.schedule.schedulemicroservice.model","com.route.routemicroservice.model"})
public class ScheduleMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleMicroserviceApplication.class, args);
	}

}

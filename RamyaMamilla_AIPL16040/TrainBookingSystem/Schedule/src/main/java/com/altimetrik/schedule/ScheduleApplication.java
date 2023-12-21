package com.altimetrik.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.altimetrik.Train.model","com.altimetrik.Route.model","com.altimetrik.Schedule.model"})
public class ScheduleApplication {

	public static void main(String[] args) {

		SpringApplication.run(ScheduleApplication.class, args);
	}

}

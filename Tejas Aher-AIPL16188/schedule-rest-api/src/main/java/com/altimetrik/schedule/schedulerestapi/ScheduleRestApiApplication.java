package com.altimetrik.schedule.schedulerestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ScheduleRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleRestApiApplication.class, args);
	}

}

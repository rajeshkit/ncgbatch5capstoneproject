package com.altimetrik.schedulemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EntityScan(basePackages =  {"com.altimetrik.trainmicroservice", "com.altimetrik.routemicroservice", "com.altimetrik.schedulemicroservice"}) // Replace with the actual base package
public class ScheduleMicroserviceApplication{
	public static void main(String[] args) {
		SpringApplication.run(ScheduleMicroserviceApplication.class, args);
	}
}

package com.altimetrik.schedulemicroservice;

import com.altimetrik.schedulemicroservice.service.ScheduleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ScheduleMicroserviceApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ScheduleMicroserviceApplication.class, args);
		ScheduleServiceImpl scheduleServiceImpl = context.getBean(ScheduleServiceImpl.class);
		scheduleServiceImpl.printBaseUrl();
	}
}
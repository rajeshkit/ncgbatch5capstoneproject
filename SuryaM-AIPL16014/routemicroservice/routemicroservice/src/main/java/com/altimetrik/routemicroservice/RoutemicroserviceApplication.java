package com.altimetrik.routemicroservice;

import com.altimetrik.routemicroservice.service.RouteServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RoutemicroserviceApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(RoutemicroserviceApplication.class, args);
		RouteServiceImpl myService = context.getBean(RouteServiceImpl.class);
		myService.printBaseUrl();

	}
}

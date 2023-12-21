package com.altimetrik.route.routerestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RouteRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteRestApiApplication.class, args);
	}

}

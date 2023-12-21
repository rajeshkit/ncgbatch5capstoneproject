package com.altimetrik.train.trainrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TrainRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainRestApiApplication.class, args);
	}

}

package com.booking_details.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.booking_details.train.model","com.booking_details.route.model","com.booking_details.schedule.model"})
public class ScheduleMain {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleMain.class, args);
    }

}

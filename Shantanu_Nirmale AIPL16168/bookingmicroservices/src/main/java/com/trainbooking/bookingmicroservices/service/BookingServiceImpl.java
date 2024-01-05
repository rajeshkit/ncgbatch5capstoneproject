package com.trainbooking.bookingmicroservices.service;

import com.trainbooking.bookingmicroservices.model.*;
import com.trainbooking.bookingmicroservices.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingServiceImpl implements  BookingService{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BookingRepository bookingRepository;

    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    public Booking bookingTrainTicket(BookingRequest bookingRequest){

        int trainNumber = bookingRequest.getTrainNumber();

        Train trainResponseObject = restTemplate.getForObject("http://localhost:8080/train-api/train/" + trainNumber, Train.class);
        logger.info("Response received from train-api{}",trainResponseObject);

        int routeId = bookingRequest.getRouteId();

        Route routeResponseObject = restTemplate.getForObject("http://localhost:8181/route-api/route/" + routeId, Route.class);
        logger.info("Response received from route-api{}", routeResponseObject);

        int scheduleId = bookingRequest.getScheduleId();

        Schedule scheduleResponseObject = restTemplate.getForObject("http://localhost:9090/schedule-api/schedule/" + scheduleId, Schedule.class);
        logger.info("Response received from schedule-api{}", scheduleResponseObject);

        return Booking.builder()
                .bookingId(bookingRequest.getBookingId())
                .bookingBy(bookingRequest.getBookingBy())
                .email(bookingRequest.getEmail())
                .train(trainResponseObject)
                .route(routeResponseObject)
                .schedule(scheduleResponseObject).build();
    }
}

package com.trainbooking.bookingmicroservices.controller;

import com.trainbooking.bookingmicroservices.model.Booking;
import com.trainbooking.bookingmicroservices.model.BookingRequest;
import com.trainbooking.bookingmicroservices.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking-api")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping(value = "/booking")
    public Booking bookingTrainTicket(@RequestBody BookingRequest bookingRequest){
        return bookingService.bookingTrainTicket(bookingRequest);
    }
}

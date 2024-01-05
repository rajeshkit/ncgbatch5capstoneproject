package com.trainbooking.bookingmicroservices.service;

import com.trainbooking.bookingmicroservices.model.Booking;
import com.trainbooking.bookingmicroservices.model.BookingRequest;

public interface BookingService {
   Booking bookingTrainTicket(BookingRequest bookingRequest);
}

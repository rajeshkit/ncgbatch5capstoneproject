package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.BookingIdAlreadyExistsException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.Booking;

import java.util.List;

public interface BookingService {

    Booking getBookingById(Long bookingId) throws BookingIdAlreadyExistsException;

    List<Booking> getAllBookings();

    String deleteBooking(Long bookingId);

    Booking addBooking(String passengerName, Long scheduleId) throws ScheduleIdNotExistsException, BookingIdAlreadyExistsException;

}

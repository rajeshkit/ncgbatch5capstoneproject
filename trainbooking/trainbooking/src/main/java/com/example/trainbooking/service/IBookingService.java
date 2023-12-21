package com.example.trainbooking.service;

import com.example.trainbooking.entity.Booking;
import com.example.trainbooking.entity.Customer;
import com.example.trainbooking.exception.CustomerIdNotFoundException;

import java.util.List;
public interface IBookingService {
    boolean bookTrain(Booking booking);
    List<Customer> getCustomerBookings(Long customerId) throws CustomerIdNotFoundException;
}

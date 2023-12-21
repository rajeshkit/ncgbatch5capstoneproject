package com.example.trainbooking.service;

import com.example.trainbooking.entity.Booking;
import com.example.trainbooking.entity.Customer;
import com.example.trainbooking.exception.CustomerIdNotFoundException;
import com.example.trainbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    public boolean bookTrain(Booking booking) {
        bookingRepository.save(booking);
        return true;
    }
    public List<Customer> getCustomerBookings(Long customerId) throws CustomerIdNotFoundException {
            List<Booking> bookings = bookingRepository.findByCustomerId(customerId);
        return bookings.stream()
                .filter(Objects::nonNull)
                .map(Booking::getCustomer)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }

    }
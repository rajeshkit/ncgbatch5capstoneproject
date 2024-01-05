package com.trainbooking.bookingmicroservices.repository;

import com.trainbooking.bookingmicroservices.model.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingRequest, Integer> {
}

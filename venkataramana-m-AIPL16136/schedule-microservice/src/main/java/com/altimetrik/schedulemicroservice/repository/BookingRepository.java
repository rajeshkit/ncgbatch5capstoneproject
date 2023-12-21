package com.altimetrik.schedulemicroservice.repository;

import com.altimetrik.schedulemicroservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {

}

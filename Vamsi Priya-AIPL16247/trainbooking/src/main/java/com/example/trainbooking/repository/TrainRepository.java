package com.example.trainbooking.repository;

import com.example.trainbooking.entity.TrainDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface TrainRepository extends JpaRepository<TrainDetails,Integer> {
    List<TrainDetails> findBySourceAndDestinationAndDepartureDate(String source, String destination, Date departureDate);
}

package com.Booking.train.repository;

import com.Booking.train.model.TrainResources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<TrainResources,Long> {
}

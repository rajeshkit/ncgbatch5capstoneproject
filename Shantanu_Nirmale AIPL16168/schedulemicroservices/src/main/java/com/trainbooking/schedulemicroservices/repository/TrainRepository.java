package com.trainbooking.schedulemicroservices.repository;

import com.trainbooking.schedulemicroservices.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {
}

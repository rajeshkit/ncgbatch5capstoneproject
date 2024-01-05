package com.trainbooking.trainmicroservices.repository;

import com.trainbooking.trainmicroservices.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {
}

package com.altimetrik.trainbooking.repository;

import com.altimetrik.trainbooking.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {
}
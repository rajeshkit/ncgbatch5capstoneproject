package com.altimetrikfinalproject.trainmicroservice.repository;

import com.altimetrikfinalproject.trainmicroservice.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {

}

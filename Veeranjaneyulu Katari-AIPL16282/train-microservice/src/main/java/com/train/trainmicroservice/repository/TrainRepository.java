package com.train.trainmicroservice.repository;

import com.train.trainmicroservice.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {
}

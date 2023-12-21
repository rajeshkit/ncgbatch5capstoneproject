package com.altimetrik.train.trainrestapi.repository;

import com.altimetrik.train.trainrestapi.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, String> {
}

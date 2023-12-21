package com.altimetrik.Train.repository;

import com.altimetrik.Train.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {
}

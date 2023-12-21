package com.altimetrik.schedule.repository;

import com.altimetrik.schedule.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {

}

package com.altimetrik.SCHEDULE.repository;


import com.altimetrik.SCHEDULE.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {
}

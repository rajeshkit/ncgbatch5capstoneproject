package com.altimetrik.trainschedule.repository;

import com.altimetrik.trainschedule.modle.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {
}

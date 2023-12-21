package com.altimetrik.schedule.schedulerestapi.repository;

import com.altimetrik.schedule.schedulerestapi.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,String>{
}

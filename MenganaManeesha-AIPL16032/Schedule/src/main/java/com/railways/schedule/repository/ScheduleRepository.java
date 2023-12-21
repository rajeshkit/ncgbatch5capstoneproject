package com.railways.schedule.repository;

import com.railways.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findByTrain_TrainNumber(Long trainNumber);
}

package com.altimetrik.schedule.repository;

import com.altimetrik.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByTrain_trainNo(int trainNum);
}

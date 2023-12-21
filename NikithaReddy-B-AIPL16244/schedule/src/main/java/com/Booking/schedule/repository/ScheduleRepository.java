package com.Booking.schedule.repository;

import com.Booking.schedule.model.ScheduleResources;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleResources,Long> {
    List<ScheduleResources> findByTrain_trainNumber(Long trainNumber);
}
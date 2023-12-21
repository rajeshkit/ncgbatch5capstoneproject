package com.altimetrik.SCHEDULE.repository;

import com.altimetrik.SCHEDULE.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}

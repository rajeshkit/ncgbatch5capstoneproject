package com.altimetrik.schedule.schedulerestapi.repository;

import com.altimetrik.schedule.schedulerestapi.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
}

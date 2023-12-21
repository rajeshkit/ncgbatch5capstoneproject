package com.rajaparvathi.schedule.repository;

import com.rajaparvathi.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
}

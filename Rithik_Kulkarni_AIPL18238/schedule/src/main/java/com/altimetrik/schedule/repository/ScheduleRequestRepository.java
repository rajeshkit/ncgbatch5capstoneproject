package com.altimetrik.schedule.repository;

import com.altimetrik.schedule.model.ScheduleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRequestRepository extends JpaRepository<ScheduleRequest, Integer> {
}

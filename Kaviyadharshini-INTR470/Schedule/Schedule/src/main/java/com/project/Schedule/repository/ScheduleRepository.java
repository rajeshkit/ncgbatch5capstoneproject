package com.project.Schedule.repository;

import com.project.Schedule.model.ScheduleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleRequest,Integer> {



}


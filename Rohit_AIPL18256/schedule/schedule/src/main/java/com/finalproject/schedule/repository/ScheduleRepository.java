package com.finalproject.schedule.repository;

import com.finalproject.schedule.model.Schedule;
import com.finalproject.schedule.model.ScheduleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleRequest,Integer> {



}

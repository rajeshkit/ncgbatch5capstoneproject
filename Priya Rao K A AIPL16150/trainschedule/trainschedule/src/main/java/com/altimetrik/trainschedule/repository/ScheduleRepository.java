package com.altimetrik.trainschedule.repository;

import com.altimetrik.trainschedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {

}
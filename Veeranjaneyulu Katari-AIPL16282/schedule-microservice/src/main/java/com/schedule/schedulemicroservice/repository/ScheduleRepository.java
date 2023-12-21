package com.schedule.schedulemicroservice.repository;

import com.schedule.schedulemicroservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {

}


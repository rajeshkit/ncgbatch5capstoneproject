package com.altimetrik.schedulemicroservice.repository;

import com.altimetrik.schedulemicroservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
}

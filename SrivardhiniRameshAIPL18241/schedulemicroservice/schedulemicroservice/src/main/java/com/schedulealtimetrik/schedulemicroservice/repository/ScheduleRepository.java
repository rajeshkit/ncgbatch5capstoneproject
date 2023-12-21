package com.schedulealtimetrik.schedulemicroservice.repository;

import com.schedulealtimetrik.schedulemicroservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {

}

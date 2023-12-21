package com.altimetrik.schedulemicroservice.respository;

import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository <ScheduleRequest,Integer>{
}

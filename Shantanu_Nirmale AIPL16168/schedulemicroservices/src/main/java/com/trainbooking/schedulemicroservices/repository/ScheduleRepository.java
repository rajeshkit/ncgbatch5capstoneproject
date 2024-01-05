package com.trainbooking.schedulemicroservices.repository;

import com.trainbooking.schedulemicroservices.model.ScheduleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleRequest, Integer> {

}

package com.schedule.schedulemicroservice.service;

import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScheduleService {
    public Schedule addSchedule(ScheduleRequest scheduleRequest);
    public List<Schedule> getAllSchedules();
    public Schedule getScheduleById(int scheduleId);
    public Schedule updateSchedule(int scheduleId, ScheduleRequest ScheduleRequest);
    public void deleteSchedule(int scheduleId);
}

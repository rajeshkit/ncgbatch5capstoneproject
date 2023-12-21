package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule addSchedule(NewScheduleRequest train) ;
    List<Schedule> getAllSchedule();
    Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistsException;
    Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException;
    String deleteScheduleById(int scheduleId) throws ScheduleIdNotExistsException;
}

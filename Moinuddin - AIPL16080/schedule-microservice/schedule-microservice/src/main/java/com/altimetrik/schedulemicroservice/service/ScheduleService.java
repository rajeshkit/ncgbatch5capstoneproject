package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exceptions.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule addSchedule(Schedule schedule) throws Exception;

    List<Schedule> getAllSchedules();

    Schedule getScheduleById(String scheduleId) throws ScheduleIdNotExistsException;

    Schedule updateSchedule(Schedule schedule) throws Exception;

    String deleteScheduleById(String scheduleId) throws ScheduleIdNotExistsException;
}

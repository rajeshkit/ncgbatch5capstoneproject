package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedule.model.NewScheduleRequest;
import com.altimetrik.schedule.model.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule addSchedule(NewScheduleRequest newScheduleRequest);
    List<Schedule> getAllSchedules();
    Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistsException;
    Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException;
    String deleteSchedule(int scheduleId) throws ScheduleIdNotExistsException;

}

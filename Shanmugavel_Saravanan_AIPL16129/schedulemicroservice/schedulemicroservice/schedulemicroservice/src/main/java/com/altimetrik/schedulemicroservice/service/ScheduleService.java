package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.ScheduleNotFoundException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(NewScheduleRequest newScheduleRequest);
    List<Schedule> getAllSchedules();
    Schedule getScheduleById(int scheduleId) throws ScheduleNotFoundException;
    Schedule updateSchedule(Schedule schedule) throws ScheduleNotFoundException;
    String deleteScheduleById(int scheduleId) throws ScheduleNotFoundException;
}

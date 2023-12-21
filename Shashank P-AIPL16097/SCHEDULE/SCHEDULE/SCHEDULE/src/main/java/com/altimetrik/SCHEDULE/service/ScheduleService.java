package com.altimetrik.SCHEDULE.service;

import com.altimetrik.SCHEDULE.exception.RouteNotFoundException;
import com.altimetrik.SCHEDULE.exception.ScheduleIdNotExistsException;
import com.altimetrik.SCHEDULE.exception.TrainNotFoundException;
import com.altimetrik.SCHEDULE.model.Schedule;
import com.altimetrik.SCHEDULE.model.ScheduleRequest;

import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(ScheduleRequest scheduleRequest) throws TrainNotFoundException, RouteNotFoundException;
    List<Schedule> getAllSchedules();
    Schedule getScheduleById(int trainId) throws ScheduleIdNotExistsException;
    Schedule updateSchedule(Schedule train) throws ScheduleIdNotExistsException;
    String deleteScheduleById(int trainId) throws ScheduleIdNotExistsException;
}

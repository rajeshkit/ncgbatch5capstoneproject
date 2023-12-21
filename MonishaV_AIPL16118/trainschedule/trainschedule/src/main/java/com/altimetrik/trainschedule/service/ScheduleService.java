package com.altimetrik.trainschedule.service;

import com.altimetrik.trainschedule.exception.RouteIdNotExistsException;
import com.altimetrik.trainschedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.trainschedule.exception.TrainNoNotExistsException;
import com.altimetrik.trainschedule.model.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule addSchedule(Schedule schedule) throws TrainNoNotExistsException, RouteIdNotExistsException;

    List<Schedule> getAllSchedule();

    Schedule getScheduleById(String scheduleId) throws ScheduleIdNotExistsException;

    Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException, TrainNoNotExistsException, RouteIdNotExistsException;

    String deleteScheduleById(String scheduleId) throws ScheduleIdNotExistsException;

}

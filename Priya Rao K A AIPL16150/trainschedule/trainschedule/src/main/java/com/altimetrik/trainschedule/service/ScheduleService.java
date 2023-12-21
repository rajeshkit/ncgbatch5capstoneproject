package com.altimetrik.trainschedule.service;

import com.altimetrik.trainschedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.trainschedule.model.Schedule;
import com.altimetrik.trainschedule.model.ScheduleRequest;

import java.util.List;

public interface ScheduleService {


    Schedule addSchedule(ScheduleRequest scheduleRequest);

    List<Schedule> getAllSchedule();

    Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistsException;

    Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException;

    String deleteScheduleById(int scheduleId) throws ScheduleIdNotExistsException;
}

package com.railways.schedule.service;

import com.railways.schedule.exception.ScheduleNotFind;
import com.railways.schedule.model.NewScheduleResource;
import com.railways.schedule.model.Schedule;

import java.util.List;


public interface ScheduleService {
    Schedule addSchedule(NewScheduleResource schedule);

    List<Schedule> getSchedule();
    Schedule getScheduleById(long scheduleId) throws ScheduleNotFind;

    Schedule updateSchedule(Schedule schedule) throws ScheduleNotFind;


    String deleteschedule(long scheduleId) throws ScheduleNotFind;


    List<Schedule> getSchedulesByTrainNumber(Long trainNumber);
}

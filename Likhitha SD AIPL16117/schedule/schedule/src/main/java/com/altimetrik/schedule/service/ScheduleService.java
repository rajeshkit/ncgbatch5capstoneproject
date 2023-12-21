package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.ScheduleNotExistsException;
import com.altimetrik.schedule.exception.TrainNotExistsException;
import com.altimetrik.schedule.model.Schedule;
import java.util.List;


public interface ScheduleService {
    Schedule addSchedule(Schedule schedule);

    List<Schedule> getAllSchedules();

    Schedule getScheduleById(int productId) throws ScheduleNotExistsException;

    Schedule updateSchedule(Schedule schedule) throws ScheduleNotExistsException, TrainNotExistsException;

    String deleteScheduleById(int scheduleId) throws ScheduleNotExistsException;
}
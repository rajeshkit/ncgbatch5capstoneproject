package com.altimetrik.trainschedule.service;

import com.altimetrik.trainschedule.exception.RouteIdNotFoundException;
import com.altimetrik.trainschedule.exception.ScheduleIdNotFoundException;
import com.altimetrik.trainschedule.exception.TrainNumberNotFoundException;
import com.altimetrik.trainschedule.modle.NewSchedule;
import com.altimetrik.trainschedule.modle.Schedule;

import java.util.List;

public interface ScheduleService {
    public Schedule addSchedule(NewSchedule newschedule) throws TrainNumberNotFoundException, RouteIdNotFoundException;

    public List<Schedule> getAllSchedule();

    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotFoundException;

    public Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotFoundException;

    public String deleteScheduleById(int scheduleId) throws ScheduleIdNotFoundException;

}


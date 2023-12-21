package com.altimetrik.schedule.service;

import com.altimetrik.route.exception.RouteIDNotFoundException;
import com.altimetrik.schedule.exception.ScheduleIDNotFoundException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.train.exception.TrainNumberNotFoundException;

import java.util.List;

public interface ScheduleService {
    public Schedule addSchedule(ScheduleRequest scheduleRequest) throws TrainNumberNotFoundException, RouteIDNotFoundException;
    public List<Schedule> viewAllSchedules();
    public Schedule getScheduleById(int scheduleId) throws ScheduleIDNotFoundException;
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIDNotFoundException;
    public String deleteScheduleById(int scheduleId) throws ScheduleIDNotFoundException;

    public List<Schedule> getScheduleByTrainNo(int trainNum);
}

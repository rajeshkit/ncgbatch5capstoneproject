package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.RouteNotExistsException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.exception.TrainNotExistsException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleInput;

import java.util.List;

public interface ScheduleService {
    public Schedule addSchedule(ScheduleInput schedule2) throws TrainNotExistsException, RouteNotExistsException;
    public List<Schedule> getAllSchedule();
    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistsException;
    public Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException;
    public String deleteSchedule(int scheduleId) throws ScheduleIdNotExistsException;
}

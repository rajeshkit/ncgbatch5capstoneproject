package com.schedulemicroservice.service;

import com.schedulemicroservice.exception.RouteNotFoundException;
import com.schedulemicroservice.exception.ScheduleIdDoesNotExistException;
import com.schedulemicroservice.exception.TrainNotFoundException;
import com.schedulemicroservice.model.Schedule;
import com.schedulemicroservice.model.ScheduleRequest;

import java.util.List;

public interface ScheduleService {
    Schedule createSchedule(ScheduleRequest scheduleRequest) throws TrainNotFoundException, RouteNotFoundException;
    List<Schedule> getAllSchedules();
    Schedule getScheduleDetailsById(int scheduleId) throws ScheduleIdDoesNotExistException;
    Schedule updateScheduleDetails(int scheduleId, ScheduleRequest scheduleRequest) throws ScheduleIdDoesNotExistException;
    String deleteScheduleDetails(int scheduleId) throws ScheduleIdDoesNotExistException;
}
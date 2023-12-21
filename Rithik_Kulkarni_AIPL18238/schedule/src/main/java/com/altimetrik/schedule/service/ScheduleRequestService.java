package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.ScheduleIdNotExistException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;

import java.util.List;

public interface ScheduleRequestService {

    public Schedule addSchedule(ScheduleRequest scheduleRequest);

    public List<Schedule> getAllScheduleRequests();

    public Schedule getScheduleRequestByScheduleId(int scheduleId) throws ScheduleIdNotExistException;

    public ScheduleRequest updateScheduleRequest(ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException;

    public String deleteScheduleByScheduleId(int scheduleId) throws ScheduleIdNotExistException;
}

package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import jakarta.validation.constraints.Null;

import java.util.List;

public interface ScheduleService {
    public Schedule addScheduleRequest(ScheduleRequest scheduleRequest);

    public List<ScheduleRequest> getAllScheduleDetails();

    public ScheduleRequest getScheduleById(int scheduleId) throws ScheduleIdNotExistException;

    public ScheduleRequest updateScheduleDetails(ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException;

    public String deleteScheduleById(int scheduleId) throws ScheduleIdNotExistException;
}

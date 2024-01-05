package com.trainbooking.schedulemicroservices.service;

import com.trainbooking.schedulemicroservices.exception.ScheduleIdNotExistException;
import com.trainbooking.schedulemicroservices.model.Schedule;
import com.trainbooking.schedulemicroservices.model.ScheduleRequest;

import java.util.List;

public interface ScheduleService {
    Schedule addNewScheduleRequest(ScheduleRequest scheduleRequest);
    List<ScheduleRequest> getAllScheduleDetails();
    ScheduleRequest getScheduleByScheduleId(int scheduleId) throws ScheduleIdNotExistException;
    ScheduleRequest updateScheduleDetails(ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException;
    String deleteScheduleByScheduleId(int scheduleId) throws ScheduleIdNotExistException;
}

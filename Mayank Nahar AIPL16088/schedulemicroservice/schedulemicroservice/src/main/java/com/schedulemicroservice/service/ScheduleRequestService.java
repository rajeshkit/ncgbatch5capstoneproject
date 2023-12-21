package com.schedulemicroservice.service;

import com.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.schedulemicroservice.model.Schedule;
import com.schedulemicroservice.model.ScheduleRequest;

import java.util.List;

public interface ScheduleRequestService {
     Schedule addScheduleDetails(ScheduleRequest scheduleRequest);

     List<ScheduleRequest> getAllScheduleRequest();


     ScheduleRequest getScheduleRequestById(int trainId) throws ScheduleIdNotExistException;

     ScheduleRequest updateScheduleRequest(ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException;

     String deleteScheduleRequestById(int trainId) throws ScheduleIdNotExistException;
}

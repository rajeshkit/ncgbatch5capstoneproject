package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdAlreadyExistsException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Schedule;

import java.util.List;

public interface ScheduleService {

        Schedule addSchedule(NewScheduleRequest newScheduleRequest) throws ScheduleIdNotExistsException, ScheduleIdAlreadyExistsException;

        Schedule getScheduleById(long scheduleId) throws ScheduleIdNotExistsException;

        List<Schedule> getAllSchedules();

        Schedule updateSchedule(long scheduleId, NewScheduleRequest updatedSchedule) throws ScheduleIdNotExistsException;

        String deleteScheduleById(long scheduleId) throws ScheduleIdNotExistsException;}



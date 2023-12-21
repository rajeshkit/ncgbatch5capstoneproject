package com.schedulealtimetrik.schedulemicroservice.service;

import com.schedulealtimetrik.schedulemicroservice.exception.ScheduleIdAlreadyExistException;
import com.schedulealtimetrik.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.schedulealtimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.schedulealtimetrik.schedulemicroservice.model.Schedule;

import javax.validation.Valid;
import java.util.List;

public interface ScheduleService {
    Schedule addSchedule(@Valid NewScheduleRequest schedule) throws ScheduleIdNotExistException, ScheduleIdAlreadyExistException;
    List<Schedule> getAllSchedules();
    Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExistException;
    Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistException;
    String deleteScheduleById(int scheduleId) throws ScheduleIdNotExistException;

}

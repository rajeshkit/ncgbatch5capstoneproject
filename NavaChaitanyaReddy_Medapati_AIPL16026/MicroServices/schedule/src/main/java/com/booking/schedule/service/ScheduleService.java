package com.booking.schedule.service;

import com.booking.route.exception.RouteIdNotExistsException;
import com.booking.schedule.exception.ScheduleIdNotExistsException;
import com.booking.schedule.model.NewScheduleRequest;
import com.booking.schedule.model.Schedule;
import com.booking.train.exception.TrainNumberNotExistsException;

import java.util.List;

public interface ScheduleService {

     Schedule addScheduleResources(NewScheduleRequest newScheduleRequest) throws RouteIdNotExistsException, TrainNumberNotExistsException;

     Schedule getScheduleResourcesById(Long scheduleId) throws ScheduleIdNotExistsException;

     List<Schedule> getAllScheduleResources();

     Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExistsException;

     String deleteScheduleById(Long scheduleId) throws ScheduleIdNotExistsException;

}

package com.Booking.schedule.service;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.schedule.customexception.ScheduleNotFind;
import com.Booking.schedule.model.Schedule;
import com.Booking.schedule.model.ScheduleResources;
import com.Booking.train.customexception.TrainIdNotFoundException;

import java.util.List;

public interface ScheduleService {
    ScheduleResources addSchedule(Schedule schedule) throws TrainIdNotFoundException, RouteNotFindException;

    List<ScheduleResources> getSchedule();
    ScheduleResources getScheduleById(long scheduleId) throws ScheduleNotFind;

    ScheduleResources updateSchedule(ScheduleResources schedule) throws ScheduleNotFind;


    String deleteschedule(long scheduleId) throws ScheduleNotFind;


    List<ScheduleResources> getSchedulesBytrainNumber(Long trainNumber);
}

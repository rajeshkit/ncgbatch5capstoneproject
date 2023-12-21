package com.railwaybooking.Schedule.service;

import com.railwaybooking.Route.exception.RouteIdNotFoundException;
import com.railwaybooking.Schedule.exception.ScheduleNotFoundException;
import com.railwaybooking.Schedule.model.NewScheduleInfo;
import com.railwaybooking.Schedule.model.Schedule;
import com.railwaybooking.Train.exception.TrainNumberNotExistException;


import java.util.List;


public interface ScheduleService {
    Schedule addSchedule(NewScheduleInfo schedule);

    List<Schedule> getSchedule();

    Schedule getScheduleById(long scheduleId) throws ScheduleNotFoundException;

    Schedule updateSchedule(Schedule schedule) throws ScheduleNotFoundException;

    String deleteScheduleById(long scheduleId) throws ScheduleNotFoundException;

    List<Schedule> getScheduleInfoByTrainNumber(Long trainNumber);
}

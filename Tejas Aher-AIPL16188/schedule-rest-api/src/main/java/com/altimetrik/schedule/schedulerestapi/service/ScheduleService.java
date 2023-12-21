package com.altimetrik.schedule.schedulerestapi.service;

import com.altimetrik.schedule.schedulerestapi.exception.RouteIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.exception.ScheduleIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.exception.TrainIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.model.NewScheduleRequest;
import com.altimetrik.schedule.schedulerestapi.model.Schedule;

import java.util.List;

public interface ScheduleService {

    public Schedule addScheduleRequest(NewScheduleRequest newScheduleRequest) throws TrainIdNotFoundException, RouteIdNotFoundException;

    public List<Schedule> getAllSchedule();

    public Schedule getScheduleById(String scheduleId) throws ScheduleIdNotFoundException;

    public Schedule updateSchedule(NewScheduleRequest newScheduleRequest, String scheduleId) throws ScheduleIdNotFoundException;

    public String deleteById(String scheduleId) throws ScheduleIdNotFoundException;

}


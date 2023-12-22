package com.finalproject.schedule.service;

import com.finalproject.schedule.exception.ScheduleNotFoundException;
import com.finalproject.schedule.model.Schedule;
import com.finalproject.schedule.model.ScheduleRequest;

import java.util.List;

public interface ScheduleService {

    public Schedule addSchedule(ScheduleRequest scheduleRequest);

    public ScheduleRequest getScheduleById(int scheduleId);

    public List<ScheduleRequest> getSchedule();


    public String deleteSchedule(int scheduleId) throws ScheduleNotFoundException;
}

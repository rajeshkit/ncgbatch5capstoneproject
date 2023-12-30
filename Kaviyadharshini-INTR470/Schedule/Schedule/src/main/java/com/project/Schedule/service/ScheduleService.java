package com.project.Schedule.service;

import com.project.Schedule.exception.ScheduleNotFoundException;
import com.project.Schedule.model.Schedule;
import com.project.Schedule.model.ScheduleRequest;

import java.util.List;

public interface ScheduleService {

    public Schedule addSchedule(ScheduleRequest scheduleRequest);

    public ScheduleRequest getScheduleById(int scheduleId);

    public List<ScheduleRequest> getSchedule();


    public String deleteSchedule(int scheduleId) throws ScheduleNotFoundException;
}

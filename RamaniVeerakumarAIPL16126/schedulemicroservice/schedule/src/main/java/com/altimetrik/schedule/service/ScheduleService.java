package com.altimetrik.schedule.service;

import com.altimetrik.schedule.exception.ScheduleIdNotExitsException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import java.util.List;

public interface ScheduleService {
    public Schedule getScheduleById(int scheduleId) throws ScheduleIdNotExitsException;
    public Schedule addSchedule(ScheduleRequest scheduleRequest);


    List<Schedule> getAllSchedule();
    Schedule updateSchedule(Schedule schedule) throws ScheduleIdNotExitsException;
    String deleteScheduleById(int scheduleId) throws ScheduleIdNotExitsException;


}

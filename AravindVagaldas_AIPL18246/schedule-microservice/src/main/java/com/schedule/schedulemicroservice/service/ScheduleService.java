package com.schedule.schedulemicroservice.service;

import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;


import java.util.List;

public interface ScheduleService {
    public Schedule addSchedule(ScheduleRequest scheduleRequest);
    public List<Schedule> getAllSchedules();
    public Schedule getScheduleById(int scheduleId);
    public Schedule updateSchedule(Schedule schedule);
    public String deleteScheduleByScheduleId(int scheduleId);

  List<Schedule> getScheduleByTrainNo(int trainNumber);
}

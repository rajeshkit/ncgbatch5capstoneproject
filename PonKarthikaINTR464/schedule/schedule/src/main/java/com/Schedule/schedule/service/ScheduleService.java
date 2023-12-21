package com.Schedule.schedule.service;

import com.Schedule.schedule.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Schedule addSchedule(Schedule schedule);

    List<Schedule> getAllSchedules();

    Optional<Schedule> getScheduleById(int scheduleId);

    Schedule updateSchedule(Schedule schedule);

    String deleteScheduleById(int scheduleId);
}

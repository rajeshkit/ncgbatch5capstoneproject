package com.altimetrik.schedule.service;

import com.altimetrik.schedule.Dto.ScheduleResponse;
import com.altimetrik.schedule.entity.Schedule;
import com.altimetrik.schedule.exception.InvalidRouteException;
import com.altimetrik.schedule.exception.InvalidTrainException;

import java.util.List;

public interface ScheduleService {
    public Schedule addSchedule(Schedule schedule) throws InvalidTrainException, InvalidRouteException;
    public ScheduleResponse getScheduleById(int scheduleId) throws InvalidTrainException, InvalidRouteException;
    public List<ScheduleResponse> getAllSchedule() throws InvalidTrainException, InvalidRouteException;
    public Schedule updateSchedule(Schedule schedule) throws InvalidTrainException, InvalidRouteException;
    public String deleteSchedule(int scheduleId);
}

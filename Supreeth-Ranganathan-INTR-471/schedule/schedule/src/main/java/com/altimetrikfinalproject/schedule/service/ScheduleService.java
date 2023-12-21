package com.altimetrikfinalproject.schedule.service;

import com.altimetrikfinalproject.schedule.entity.NewScheduleRequest;
import com.altimetrikfinalproject.schedule.entity.ScheduleResponse;
import com.altimetrikfinalproject.schedule.exception.NoScheduleFoundException;
import com.altimetrikfinalproject.schedule.exception.RouteIdDoesNotExistException;
import com.altimetrikfinalproject.schedule.exception.TrainIdDoesNotExistException;

import java.util.List;

public interface ScheduleService {
    public NewScheduleRequest addNewSchedule(NewScheduleRequest newScheduleRequest);
    public List<NewScheduleRequest> getAllSchedule();

    public NewScheduleRequest updateSchedule(NewScheduleRequest newScheduleRequest) throws TrainIdDoesNotExistException;
    public NewScheduleRequest deleteSchedule(int trainId);



    public ScheduleResponse addNewScheduleRequest(NewScheduleRequest newScheduleRequest);


    ScheduleResponse getScheduleByTrainID(int trainID, int scheduleId);

    ScheduleResponse getScheduleByRouteID(int routeId, int schedule) throws NoScheduleFoundException;
}

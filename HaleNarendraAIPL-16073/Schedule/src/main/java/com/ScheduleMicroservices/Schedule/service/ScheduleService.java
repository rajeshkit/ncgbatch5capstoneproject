package com.ScheduleMicroservices.Schedule.service;



import com.ScheduleMicroservices.Schedule.exception.ScheduleIdNotFoundException;
import com.ScheduleMicroservices.Schedule.model.NewScheduleResources;
import com.ScheduleMicroservices.Schedule.model.ScheduleResources;
import com.TrainBookingSystem.Train.Exception.TrainIdNotFoundException;


import java.util.List;

public interface ScheduleService {


    NewScheduleResources addScheduleResources(ScheduleResources scheduleResources);


    List<NewScheduleResources> findDetailByTrainId(Long trainId) throws TrainIdNotFoundException;

    NewScheduleResources getScheduleById(Long scheduleId) throws ScheduleIdNotFoundException;

    NewScheduleResources updateScheduleDetail(NewScheduleResources newScheduleResources);

    List<NewScheduleResources> getAllScheduleDetail();

    String deleteScheduleById(Long scheduleId) throws ScheduleIdNotFoundException;
}

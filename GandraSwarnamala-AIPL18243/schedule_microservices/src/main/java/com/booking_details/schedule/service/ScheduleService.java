package com.booking_details.schedule.service;

import com.booking_details.route.exception.RouteIdNotFoundException;
import com.booking_details.schedule.exception.ScheduleNotFoundException;
import com.booking_details.schedule.model.ScheduleModel;
import com.booking_details.schedule.model.ScheduleRequest;
import com.booking_details.train.exception.TrainIdNotFoundException;

import java.util.List;

public interface ScheduleService {
    ScheduleModel addScheduleDetails(ScheduleRequest scheduleRequest) throws TrainIdNotFoundException, RouteIdNotFoundException;

    List<ScheduleModel> getScheduleDetails();
    ScheduleModel getScheduleDetailsById(long scheduleId) throws ScheduleNotFoundException;

    ScheduleModel updateScheduleDetails(ScheduleRequest schedule) throws TrainIdNotFoundException,RouteIdNotFoundException,ScheduleNotFoundException;


    String deleteScheduleDetails(long scheduleId) throws ScheduleNotFoundException;


    ScheduleModel getScheduleDetailsByTrainId(Long trainNumber);

}

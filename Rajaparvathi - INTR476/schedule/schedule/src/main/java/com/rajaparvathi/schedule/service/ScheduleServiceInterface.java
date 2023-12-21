package com.rajaparvathi.schedule.service;

import com.rajaparvathi.schedule.model.Schedule;
import org.springframework.util.RouteMatcher;

public interface ScheduleServiceInterface {
    Schedule createSchedule(Schedule newScheduleRequest);

    Schedule createSchedule(Schedule scheduleRequest);
    Train fetchTrainByTrainId(String trainId);
    Route fetchRouteByRouteId(String routeId);



    }

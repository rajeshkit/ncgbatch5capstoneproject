package com.railways.schedule.services;

import com.railways.schedule.exceptions.ScheduleIdNotFoundException;
import com.railways.schedule.model.Schedule;
import com.railways.schedule.model.ScheduleDemo;

import java.util.List;

public interface ScheduleServices {


    Schedule insertScheduleDetails(ScheduleDemo scheduleDemo) ;
    List<Schedule> getAllScheduleDetails();

    Schedule getScheduleById(long routeIdToSearch)  throws ScheduleIdNotFoundException;


    Schedule updateScheduleById(Schedule schedule) throws ScheduleIdNotFoundException;

    String deleteScheduleById(long scheduleIdToDelete) throws  ScheduleIdNotFoundException;

}

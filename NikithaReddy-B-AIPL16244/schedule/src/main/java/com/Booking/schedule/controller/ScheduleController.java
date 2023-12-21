package com.Booking.schedule.controller;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.schedule.customexception.ScheduleNotFind;
import com.Booking.schedule.model.Schedule;
import com.Booking.schedule.model.ScheduleResources;
import com.Booking.schedule.service.ScheduleService;
import com.Booking.train.customexception.TrainIdNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @PostMapping("/schedule_table")
    private ScheduleResources addSchedule(@RequestBody @Valid Schedule schedule) throws  TrainIdNotFoundException, RouteNotFindException
    {
        return scheduleService.addSchedule(schedule);
    }
    @GetMapping("/schedule_table")
    private List<ScheduleResources> getSchedule()

    {
        return scheduleService.getSchedule();
    }
    @GetMapping("schedule_table/{id}")
    private ScheduleResources getScheduleById(@PathVariable("id") long scheduleId) throws ScheduleNotFind
    {
        return scheduleService.getScheduleById(scheduleId);
    }
    @PutMapping("/schedule_table")
    private ScheduleResources updateSchedule(@RequestBody ScheduleResources scheduleResources) throws ScheduleNotFind
    {
        return scheduleService.updateSchedule(scheduleResources);
    }
    @DeleteMapping("/schedule_table/{id}")
    private String deleteSchedule(@PathVariable("id") long scheduleId) throws ScheduleNotFind
    {
        return scheduleService.deleteschedule(scheduleId);
    }
    @GetMapping("/train/{trainNumber}")
    public List<ScheduleResources> getSchedulesBytrainNumber(@PathVariable Long trainNumber) throws TrainIdNotFoundException
    {
        List<ScheduleResources> scheduleList= scheduleService.getSchedulesBytrainNumber(trainNumber);
        if(scheduleList.isEmpty())
        {
            throw new TrainIdNotFoundException();
        }
        return scheduleList;
    }
}



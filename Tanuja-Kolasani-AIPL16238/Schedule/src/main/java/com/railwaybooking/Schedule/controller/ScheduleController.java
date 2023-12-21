package com.railwaybooking.Schedule.controller;


import com.railwaybooking.Route.exception.RouteIdNotFoundException;
import com.railwaybooking.Schedule.exception.ScheduleNotFoundException;
import com.railwaybooking.Schedule.model.NewScheduleInfo;
import com.railwaybooking.Schedule.model.Schedule;
import com.railwaybooking.Schedule.service.ScheduleService;
import com.railwaybooking.Train.exception.TrainNumberNotExistException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduleInfo-api")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/scheduleInfo")
    public Schedule addSchedule(@RequestBody @Valid NewScheduleInfo schedule) throws TrainNumberNotExistException, RouteIdNotFoundException {
        return scheduleService.addSchedule(schedule);
    }

    @GetMapping("/scheduleInfo")
    public List<Schedule> getSchedule(){
        return scheduleService.getSchedule();
    }

    @GetMapping("/scheduleInfo/{id}")
    public Schedule getScheduleById(@PathVariable("id") long scheduleId) throws ScheduleNotFoundException{
        return scheduleService.getScheduleById(scheduleId);
    }

    @PutMapping("/scheduleInfo")
    public Schedule updateSchedule(@RequestBody Schedule schedule) throws ScheduleNotFoundException{
        return scheduleService.updateSchedule(schedule);
    }

    @DeleteMapping("/scheduleInfo/{id}")
    public String deleteScheduleById(@PathVariable("id") long scheduleId) throws ScheduleNotFoundException{
        return scheduleService.deleteScheduleById(scheduleId);
    }

    @GetMapping("/trainInfo/{trainNumber}")
    public List<Schedule> getScheduleInfoByTrainNumber(@PathVariable Long trainNumber) throws TrainNumberNotExistException{
        List<Schedule> scheduleList=scheduleService.getScheduleInfoByTrainNumber(trainNumber);
        if(scheduleList.isEmpty()){
            throw new TrainNumberNotExistException();
        }
        return scheduleList;
    }

}

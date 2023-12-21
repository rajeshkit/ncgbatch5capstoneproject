package com.ScheduleMicroservices.Schedule.Controller;



import com.ScheduleMicroservices.Schedule.exception.ScheduleIdNotFoundException;
import com.ScheduleMicroservices.Schedule.model.NewScheduleResources;
import com.ScheduleMicroservices.Schedule.model.ScheduleResources;
import com.ScheduleMicroservices.Schedule.service.ScheduleService;
import com.TrainBookingSystem.Train.Exception.TrainIdNotFoundException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;


    @PostMapping(value = "/schedule")
    public NewScheduleResources addSchedule(@RequestBody @Valid ScheduleResources scheduleResources){

        return scheduleService.addScheduleResources(scheduleResources);
    }



    @GetMapping(value = "/train/{id}")
    public List<NewScheduleResources> findDetailByTrainId(@PathVariable("id") Long trainId) throws TrainIdNotFoundException {
        return scheduleService.findDetailByTrainId(trainId);
    }


    @GetMapping(value = "/schedule/{id}")
    public NewScheduleResources getScheduleById(@PathVariable("id") Long scheduleId) throws ScheduleIdNotFoundException {

        return scheduleService.getScheduleById(scheduleId);
    }


    @PutMapping(value = "/schedule")
    public NewScheduleResources updateScheduleDetail(@RequestBody NewScheduleResources newScheduleResources) throws ScheduleIdNotFoundException {


        return scheduleService.updateScheduleDetail(newScheduleResources);
    }


    @GetMapping(value = "/schedule")
    public List<NewScheduleResources> allScheduleDetail() {

        return scheduleService.getAllScheduleDetail();
    }



    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") Long scheduleId) throws ScheduleIdNotFoundException {

        return scheduleService.deleteScheduleById(scheduleId);
    }



}

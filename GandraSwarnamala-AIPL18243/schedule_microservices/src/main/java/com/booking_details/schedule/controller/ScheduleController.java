package com.booking_details.schedule.controller;

import com.booking_details.route.exception.RouteIdNotFoundException;
import com.booking_details.schedule.exception.ScheduleNotFoundException;
import com.booking_details.schedule.model.ScheduleModel;
import com.booking_details.schedule.model.ScheduleRequest;
import com.booking_details.schedule.service.ScheduleService;
import com.booking_details.train.exception.TrainIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/schedule-microservice")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ScheduleModel addScheduleDetails(@RequestBody ScheduleRequest scheduleRequest) throws TrainIdNotFoundException, RouteIdNotFoundException {
        return scheduleService.addScheduleDetails(scheduleRequest);
    }
    @GetMapping("/schedule")
    public List<ScheduleModel> getScheduleDetails()
    {
        return scheduleService.getScheduleDetails();
    }
    @GetMapping("schedule/{id}")
    public ScheduleModel getScheduleDetailsById(@PathVariable("id") long scheduleId) throws ScheduleNotFoundException {
        return scheduleService.getScheduleDetailsById(scheduleId);

    }
    @PutMapping("/schedule")
    public ScheduleModel updateScheduleDetails(@RequestBody ScheduleRequest scheduleRequest) throws ScheduleNotFoundException, TrainIdNotFoundException, RouteIdNotFoundException {
        return scheduleService.updateScheduleDetails(scheduleRequest);
    }
    @DeleteMapping("/schedule/{id}")
    public String deleteScheduleDetails(@PathVariable("id") long scheduleId) throws ScheduleNotFoundException {
        return scheduleService.deleteScheduleDetails(scheduleId);
    }

    @GetMapping("/train/{trainNumber}")
    public ScheduleModel getScheduleDetailsByTrainId(@PathVariable Long trainNumber) throws TrainIdNotFoundException {
        ScheduleModel scheduleModel= scheduleService.getScheduleDetailsByTrainId(trainNumber);
        if(scheduleModel==null)
        {
            throw new TrainIdNotFoundException();
        }
        return scheduleModel;
    }
}

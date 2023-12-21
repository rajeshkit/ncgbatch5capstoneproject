package com.altimetrik.schedule.controller;

import com.altimetrik.route.exception.RouteIDNotFoundException;
import com.altimetrik.schedule.exception.ScheduleIDNotFoundException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.service.ScheduleService;
import com.altimetrik.train.exception.TrainNumberNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping(value = "/schedule")
    public Schedule addSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest) throws TrainNumberNotFoundException, RouteIDNotFoundException {
        return scheduleService.addSchedule(scheduleRequest);
    }
    @GetMapping(value = "/schedule")
    public List<Schedule> viewAllSchedules(){
        return scheduleService.viewAllSchedules();
    }
    @GetMapping(value = "/schedule/{id}")
    public Schedule getScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIDNotFoundException{
        return scheduleService.getScheduleById(scheduleId);
    }
    @PutMapping(value = "/schedule")
    public Schedule updateSchedule(@RequestBody @Valid Schedule schedule) throws ScheduleIDNotFoundException{
        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIDNotFoundException{
        return scheduleService.deleteScheduleById(scheduleId);
    }

    @GetMapping("train/{trainNo}")
    public List<Schedule> getScheduleByTrainNo(@PathVariable("trainNo") int trainNum){
        List<Schedule> scheduleList=scheduleService.getScheduleByTrainNo(trainNum);
        if(scheduleList.isEmpty())
        {
            throw new TrainNumberNotFoundException("No train for selected ID");
        }
        return scheduleList;
    }
}

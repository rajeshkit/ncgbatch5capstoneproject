package com.project.Schedule.controller;

import com.project.Schedule.exception.ScheduleNotFoundException;
import com.project.Schedule.model.Schedule;
import com.project.Schedule.model.ScheduleRequest;
import com.project.Schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-restapi")

public class ScheduleControl {

    private ScheduleService scheduleService ;

    @PostMapping(value = "/schedule")
    public Schedule addSchedule(@RequestBody ScheduleRequest scheduleRequest){
        return  scheduleService.addSchedule(scheduleRequest);
    }

    @GetMapping(value = "/schedule/{scheduleId}")

    public ScheduleRequest getScheduleById(@PathVariable("scheduleId") int scheduleId){
        return scheduleService.getScheduleById(scheduleId);
    }

    @GetMapping(value = "/schedule")
    public List<ScheduleRequest> getAllScheduleDetails(){
        return scheduleService.getSchedule();
    }


    @DeleteMapping(value = "/schedule/{scheduleId}")
    public String deleteSchedule(@PathVariable("scheduleId") int scheduleId) throws ScheduleNotFoundException {
        return scheduleService.deleteSchedule(scheduleId);
    }


}


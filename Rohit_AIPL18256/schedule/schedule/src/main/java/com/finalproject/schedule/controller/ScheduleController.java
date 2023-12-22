package com.finalproject.schedule.controller;


import com.finalproject.schedule.exception.ScheduleNotFoundException;
import com.finalproject.schedule.model.Schedule;
import com.finalproject.schedule.model.ScheduleRequest;
import com.finalproject.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-restapi")

public class ScheduleController {

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

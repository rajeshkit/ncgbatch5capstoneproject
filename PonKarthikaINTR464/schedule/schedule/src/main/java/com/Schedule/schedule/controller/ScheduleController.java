package com.Schedule.schedule.controller;

import com.Schedule.schedule.model.Schedule;
import com.Schedule.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
    private ScheduleService scheduleService;
    @Autowired
    public  ScheduleController(ScheduleService scheduleService) {

        this.scheduleService = scheduleService;
    }


    @PostMapping(value = "/schedule")
    public Schedule addSchedule(@RequestBody Schedule schedule) {

        return scheduleService.addSchedule(schedule);
    }
    @GetMapping(value = "/schedule")
    public List<Schedule> getAllSchedules() {

        return scheduleService.getAllSchedules();
    }
    @GetMapping(value = "/schedule/{Id}")
    public Optional<Schedule> getScheduleById(@PathVariable("Id") int scheduleId){

        return scheduleService.getScheduleById(scheduleId);
    }
    @PutMapping(value = "/schedule")
    public Schedule updateSchedule(@RequestBody  Schedule schedule)  {

        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping(value = "/schedule/{Id}")
    public String deleteScheduleById(@PathVariable("Id") int scheduleId)   {

        return scheduleService.deleteScheduleById(scheduleId);
    }
}




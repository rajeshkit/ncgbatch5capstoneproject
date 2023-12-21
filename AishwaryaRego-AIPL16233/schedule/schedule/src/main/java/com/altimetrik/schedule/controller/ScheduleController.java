package com.altimetrik.schedule.controller;

import com.altimetrik.schedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedule.model.NewScheduleRequest;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @PostMapping(value = "/schedule")
    public Schedule addSchedule(@RequestBody @Valid NewScheduleRequest newScheduleRequest){
        return scheduleService.addSchedule(newScheduleRequest);
    }
    @GetMapping(value = "/schedule")
    public List<Schedule> getAllSchedules(){
        return scheduleService.getAllSchedules();
    }
    @GetMapping(value = "/schedule/{id}")
    public Schedule getScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistsException{
        return scheduleService.getScheduleById(scheduleId);
    }
    @PutMapping(value = "/schedule")
    public Schedule updateSchedule(@RequestBody Schedule schedule) throws ScheduleIdNotExistsException{
        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping(value="/schedule/{id}")
    public String deleteSchedule(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistsException{
        return scheduleService.deleteSchedule(scheduleId);
    }
}

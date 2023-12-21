package com.altimetrik.schedulemicroservice.controller;
import javax.validation.Valid;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdAlreadyExistsException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/schedule")
    public Schedule addSchedule(@RequestBody @Valid NewScheduleRequest newScheduleRequest) throws ScheduleIdNotExistsException, ScheduleIdAlreadyExistsException {
        return scheduleService.addSchedule(newScheduleRequest);
    }

    @GetMapping("/schedule/{scheduleId}")
    public Schedule getScheduleById(@PathVariable int scheduleId) throws ScheduleIdNotExistsException {
        return scheduleService.getScheduleById(scheduleId);
    }

    @GetMapping("/schedule")
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @PutMapping("schedule/{scheduleId}")
    public Schedule updateSchedule(@PathVariable int scheduleId, @RequestBody NewScheduleRequest updatedSchedule) throws ScheduleIdNotExistsException {
        return scheduleService.updateSchedule(scheduleId, updatedSchedule);
    }

    @DeleteMapping("schedule/{scheduleId}")
    public String deleteScheduleById(@PathVariable int scheduleId) throws ScheduleIdNotExistsException {
        return scheduleService.deleteScheduleById(scheduleId);
    }

}
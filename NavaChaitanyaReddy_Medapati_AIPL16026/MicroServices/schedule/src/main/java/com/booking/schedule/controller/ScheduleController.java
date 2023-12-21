package com.booking.schedule.controller;

import com.booking.route.exception.RouteIdNotExistsException;
import com.booking.schedule.exception.ScheduleIdNotExistsException;
import com.booking.schedule.model.NewScheduleRequest;
import com.booking.schedule.model.Schedule;
import com.booking.schedule.service.ScheduleService;
import com.booking.train.exception.TrainNumberNotExistsException;

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
    public Schedule addScheduleResources(@RequestBody @Valid NewScheduleRequest newScheduleRequest) throws RouteIdNotExistsException, TrainNumberNotExistsException {
        return scheduleService.addScheduleResources(newScheduleRequest);
    }

    @GetMapping(value = "/schedule")
    public List<Schedule> getAllScheduleResources()
    {
        return scheduleService.getAllScheduleResources();
    }
    @GetMapping(value = "/schedule/{id}")
    public Schedule getScheduleResourcesById(@PathVariable("id") Long scheduleId) throws ScheduleIdNotExistsException {
        return scheduleService.getScheduleResourcesById(scheduleId);
    }

    @PutMapping(value = "/schedule")
    public Schedule updateSchedule(@RequestBody @Valid Schedule schedule) throws ScheduleIdNotExistsException {
        return scheduleService.updateSchedule(schedule);
    }

    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") Long scheduleId) throws ScheduleIdNotExistsException {
        return scheduleService.deleteScheduleById(scheduleId);
    }

}

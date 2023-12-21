package com.altimetrik.SCHEDULE.controller;

import com.altimetrik.SCHEDULE.exception.RouteNotFoundException;
import com.altimetrik.SCHEDULE.exception.ScheduleIdNotExistsException;
import com.altimetrik.SCHEDULE.exception.TrainNotFoundException;
import com.altimetrik.SCHEDULE.model.Schedule;
import com.altimetrik.SCHEDULE.model.ScheduleRequest;
import com.altimetrik.SCHEDULE.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-train")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    @PostMapping
    public Schedule createSchedule(@RequestBody ScheduleRequest scheduleRequest) throws TrainNotFoundException, RouteNotFoundException {
        logger.info("Received a request to create schedule: {}", scheduleRequest);
        return scheduleService.createSchedule(scheduleRequest);
    }

    @GetMapping(value ="/schedule-train")
    public List<Schedule> getAllSchedule(){
        logger.info("Received request to get all schedules");
        return scheduleService.getAllSchedules();
    }
    @GetMapping(value ="/schedule-train/{id}")
    public Schedule getScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistsException {
        logger.info("Received request to get schedule by ID: {}", scheduleId);
        return scheduleService.getScheduleById(scheduleId);
    }
    @PutMapping(value ="/schedule-train")
    public Schedule updateSchedule(@RequestBody Schedule schedule) throws ScheduleIdNotExistsException {
        logger.info("Received request to update schedule: {}", schedule);
        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping(value ="/schedule-train/{id}")
    public String deleteScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistsException {
        logger.info("Received request to delete schedule by ID: {}", scheduleId);
        return scheduleService.deleteScheduleById(scheduleId);
    }
}


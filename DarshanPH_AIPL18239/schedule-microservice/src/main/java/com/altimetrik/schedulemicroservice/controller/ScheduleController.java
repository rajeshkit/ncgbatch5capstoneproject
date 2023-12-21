package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.exception.RouteNotExistsException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.exception.TrainNotExistsException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleInput;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/schedule-api")
@Validated
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    private static final Logger logger= LoggerFactory.getLogger(ScheduleController.class);
    @PostMapping(value="/schedule")
    public Schedule addSchedule(@RequestBody @Valid ScheduleInput scheduleinput) throws TrainNotExistsException, RouteNotExistsException {
        logger.info("Received a request to create schedule: {}",scheduleinput);
        return scheduleService.addSchedule(scheduleinput);
    }
    @GetMapping(value ="/schedule")
    public List<Schedule> getAllSchedule(){
        logger.info("Received request to get all schedules");
        return scheduleService.getAllSchedule();
    }
    @GetMapping(value ="/schedule/{id}")
    public Schedule getScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistsException {
        logger.info("Received request to get schedule by ID: {}", scheduleId);
        return scheduleService.getScheduleById(scheduleId);
    }
    @PutMapping(value ="/schedule")
    public Schedule updateSchedule(@RequestBody Schedule schedule) throws ScheduleIdNotExistsException {
        logger.info("Received request to update schedule: {}", schedule);
        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping(value ="/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistsException {
        logger.info("Received request to delete schedule by ID: {}", scheduleId);
        return scheduleService.deleteSchedule(scheduleId);
    }
}

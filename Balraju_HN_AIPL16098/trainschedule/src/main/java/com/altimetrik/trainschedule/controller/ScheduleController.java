package com.altimetrik.trainschedule.controller;

import com.altimetrik.trainschedule.exception.RouteIdNotFoundException;
import com.altimetrik.trainschedule.exception.ScheduleIdNotFoundException;
import com.altimetrik.trainschedule.exception.TrainNumberNotFoundException;
import com.altimetrik.trainschedule.modle.NewSchedule;
import com.altimetrik.trainschedule.modle.Schedule;
import com.altimetrik.trainschedule.service.ScheduleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    private static final Logger logger= LoggerFactory.getLogger(ScheduleController.class);
    @PostMapping(value = "/schedule")
    public Schedule addSchedule(@RequestBody NewSchedule newschedule) throws TrainNumberNotFoundException, RouteIdNotFoundException {
        logger.info("Received a request to create schedule: {}",newschedule);
        return scheduleService.addSchedule(newschedule);
    }
    @GetMapping(value = "/schedule")
    public List<Schedule> getAllSchedule()
    {
        logger.info("Received request to get all schedules");
        return scheduleService.getAllSchedule();
    }
    @GetMapping(value = "/schedule/{id}")
    public Schedule getScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotFoundException {
        logger.info("Received request to get schedule by ID: {}", scheduleId);
        return scheduleService.getScheduleById(scheduleId);
    }
    @PutMapping(value = "/schedule")
    public Schedule updateSchedule(@RequestBody  Schedule schedule) throws ScheduleIdNotFoundException {
        logger.info("Received request to update schedule: {}", schedule);
        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotFoundException {
        logger.info("Received request to delete schedule by ID: {}", scheduleId);
        return scheduleService.deleteScheduleById(scheduleId);
    }
}

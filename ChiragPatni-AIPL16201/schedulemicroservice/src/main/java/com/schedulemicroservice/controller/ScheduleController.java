package com.schedulemicroservice.controller;

import com.schedulemicroservice.exception.RouteNotFoundException;
import com.schedulemicroservice.exception.ScheduleIdDoesNotExistException;
import com.schedulemicroservice.exception.TrainNotFoundException;
import com.schedulemicroservice.model.Schedule;
import com.schedulemicroservice.model.ScheduleRequest;
import com.schedulemicroservice.service.ScheduleService;
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
    @PostMapping("/schedule")
    public Schedule createSchedule(@RequestBody ScheduleRequest scheduleRequest) throws TrainNotFoundException, RouteNotFoundException {
        logger.info("Received a request to create schedule: {}", scheduleRequest);
        return scheduleService.createSchedule(scheduleRequest);
    }

    @GetMapping("/schedule")
    public List<Schedule> getAllSchedulesDetail() {
        logger.info("Received a request to get all schedule detail.");
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/{id}")
    public Schedule getScheduleDetailsById(@PathVariable("id") int scheduleId) throws ScheduleIdDoesNotExistException {
        logger.info("Received a request to get schedule details by Schedule-Id : {}", scheduleId);
        return scheduleService.getScheduleDetailsById(scheduleId);
    }

    @PutMapping("/schedule/{id}")
    public Schedule updateScheduleDetails(@PathVariable("id") int scheduleId, @RequestBody ScheduleRequest scheduleRequest) throws ScheduleIdDoesNotExistException {
        logger.info("Received a request to update schedule details : {}", scheduleRequest);
        return scheduleService.updateScheduleDetails(scheduleId,scheduleRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteScheduleDetails(@PathVariable("id") int scheduleId) throws ScheduleIdDoesNotExistException {
        logger.info("Received a request to delete Schedule details with Schedule-Id : {}", scheduleId);
        return scheduleService.deleteScheduleDetails(scheduleId);
    }
}
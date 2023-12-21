package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.exception.ScheduleNotFoundException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

//    @PostMapping("/schedule")
//    public Schedule createSchedule(@RequestBody @Valid NewScheduleRequest newScheduleRequest){
//          //  throws TrainNotFoundException, RouteNotFoundException {
//        return scheduleService.createSchedule(newScheduleRequest);
//    }
@PostMapping("/schedule")
public ResponseEntity<Schedule> createSchedule(@RequestBody @Valid NewScheduleRequest newScheduleRequest) {
    Schedule createdSchedule = scheduleService.createSchedule(newScheduleRequest);
    return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
}

    @GetMapping("/schedule/{scheduleId}")
    public Schedule getScheduleById(@PathVariable int scheduleId) throws ScheduleNotFoundException {
        return scheduleService.getScheduleById(scheduleId);
    }

    @GetMapping("/schedule")
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @PutMapping(value = "/schedule")
    public Schedule updateSchedule(@RequestBody @Valid Schedule schedule) throws ScheduleNotFoundException {
        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") int scheduleId) throws ScheduleNotFoundException {
        return scheduleService.deleteScheduleById(scheduleId);
    }
}


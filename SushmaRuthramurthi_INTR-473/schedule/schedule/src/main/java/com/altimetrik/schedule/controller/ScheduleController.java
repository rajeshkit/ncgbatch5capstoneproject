package com.altimetrik.schedule.controller;

import com.altimetrik.schedule.Dto.ScheduleResponse;
import com.altimetrik.schedule.Dto.Train;
import com.altimetrik.schedule.entity.Schedule;
import com.altimetrik.schedule.exception.InvalidRouteException;
import com.altimetrik.schedule.exception.InvalidTrainException;
import com.altimetrik.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) throws InvalidTrainException, InvalidRouteException {
        return new ResponseEntity<>(scheduleService.addSchedule(schedule), HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable int scheduleId) throws InvalidTrainException, InvalidRouteException {
        return new ResponseEntity<>(scheduleService.getScheduleById(scheduleId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAllSchedule() throws InvalidTrainException, InvalidRouteException {
        return new ResponseEntity<>(scheduleService.getAllSchedule(), HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable("scheduleId") int scheduleId,
                                                   @RequestBody Schedule schedule) throws InvalidTrainException, InvalidRouteException {
        return new ResponseEntity<>(scheduleService.updateSchedule(schedule), HttpStatus.CREATED);
    }
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable("scheduleId") int scheduleId){
        return new ResponseEntity<>(scheduleService.deleteSchedule(scheduleId),HttpStatus.OK);
    }


}

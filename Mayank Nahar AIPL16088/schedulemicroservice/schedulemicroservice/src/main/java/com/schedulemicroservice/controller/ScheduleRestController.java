package com.schedulemicroservice.controller;


import com.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.schedulemicroservice.model.Schedule;
import com.schedulemicroservice.model.ScheduleRequest;
import com.schedulemicroservice.service.ScheduleRequestService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedule-api")
@Slf4j
public class ScheduleRestController {
@Autowired
    ScheduleRequestService scheduleRequestService;
   @PostMapping(value = "/schedule")
    public Schedule addScheduleDetails(@RequestBody @Valid ScheduleRequest scheduleRequest) {
      log.info("Request send to service class");
       return scheduleRequestService.addScheduleDetails(scheduleRequest);
    }
    @GetMapping(value = "/schedule")
    public List<ScheduleRequest> getAllScheduleRequest(){
       return scheduleRequestService.getAllScheduleRequest();
    }

    @GetMapping(value = "/schedule/{scheduleId}")
    public ScheduleRequest getScheduleRequestById(@PathVariable("scheduleId") int scheduleId) throws ScheduleIdNotExistException {
        return scheduleRequestService.getScheduleRequestById(scheduleId);
    }

    @PutMapping(value = "/schedule")
    public ScheduleRequest updateScheduleRequest(@RequestBody ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException {
        return scheduleRequestService.updateScheduleRequest(scheduleRequest);
    }

    @DeleteMapping(value = "/schedule/{scheduleId}")
    public String deleteScheduleRequestById(@PathVariable("scheduleId") int trainId) throws ScheduleIdNotExistException {
        return scheduleRequestService.deleteScheduleRequestById(trainId);
    }





}

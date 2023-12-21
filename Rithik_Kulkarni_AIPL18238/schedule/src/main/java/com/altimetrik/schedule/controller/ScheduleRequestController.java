package com.altimetrik.schedule.controller;

import com.altimetrik.schedule.exception.ScheduleIdNotExistException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.service.ScheduleRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleRequestController {

    @Autowired
    private ScheduleRequestService scheduleRequestService;

    @PostMapping(value = "/schedule")
    public Schedule addSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest){
        System.out.println("Adding Schedule.");
        return scheduleRequestService.addSchedule(scheduleRequest);
    }

    @GetMapping(value = "/schedule")
    public List<Schedule> getAllSchedules(){
        System.out.println("Get All Trains");
        return scheduleRequestService.getAllScheduleRequests();
    }

    @GetMapping(value = "/schedule/{scheduleId}")
    public Schedule getScheduleByScheduleId(@PathVariable("scheduleId") int scheduleId) throws ScheduleIdNotExistException {
        return scheduleRequestService.getScheduleRequestByScheduleId(scheduleId);
    }

    @PutMapping(value = "/schedule")
    public ScheduleRequest updateSchedule(@RequestBody ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException {
        return scheduleRequestService.updateScheduleRequest(scheduleRequest);
    }

    @DeleteMapping(value = "/schedule/{scheduleId}")
    public String deleteScheduleByScheduleId(@PathVariable("scheduleId") int scheduleId) throws ScheduleIdNotExistException {
        return scheduleRequestService.deleteScheduleByScheduleId(scheduleId);
    }

}

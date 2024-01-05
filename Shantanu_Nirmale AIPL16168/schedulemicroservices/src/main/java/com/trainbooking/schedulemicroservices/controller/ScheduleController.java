package com.trainbooking.schedulemicroservices.controller;

import com.trainbooking.schedulemicroservices.exception.ScheduleIdNotExistException;
import com.trainbooking.schedulemicroservices.model.Schedule;
import com.trainbooking.schedulemicroservices.model.ScheduleRequest;
import com.trainbooking.schedulemicroservices.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    //To add new schedule
    @PostMapping(value = "/schedule")
    public Schedule addNewScheduleRequest(@RequestBody ScheduleRequest scheduleRequest){
        return scheduleService.addNewScheduleRequest(scheduleRequest);
    }

    //To get all schedule
    @GetMapping(value = "/schedule")
    public List<ScheduleRequest> getAllScheduleDetails(){
        return scheduleService.getAllScheduleDetails();
    }

    //To get schedule by scheduleId
    @GetMapping(value = "/schedule/{scheduleId}")
    public ScheduleRequest getScheduleByScheduleId(@PathVariable("scheduleId") int scheduleId) throws ScheduleIdNotExistException {
        return scheduleService.getScheduleByScheduleId(scheduleId);
    }

    //To update schedule
    @PutMapping(value = "/schedule")
    public ScheduleRequest updateScheduleDetails(@RequestBody @Valid ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException {
        return scheduleService.updateScheduleDetails(scheduleRequest);
    }

    //To delete schedule by scheduleId
    @DeleteMapping(value = "/schedule/{scheduleId}")
    public String deleteScheduleByScheduleId(@PathVariable("scheduleId") int scheduleId) throws ScheduleIdNotExistException {
        return scheduleService.deleteScheduleByScheduleId(scheduleId);
    }

}

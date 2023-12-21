package com.altimetrik.schedulemicroservice.controller;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController
{

    @Autowired
    private ScheduleService scheduleService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleRequest.class);

    @PostMapping("/schedule")
    public Schedule addScheduleRequest(@RequestBody @Valid ScheduleRequest scheduleRequest)
    {
        logger.info("Received a request to save train Detail : {}", scheduleRequest);
        return scheduleService.addScheduleRequest(scheduleRequest);
    }

    @GetMapping(value = "/schedule")
    public List<ScheduleRequest> getAllScheduleDetails(){
        return scheduleService.getAllScheduleDetails();
    }


    @GetMapping(value = "/schedule/{scheduleId}")
    public ScheduleRequest getScheduleById(@PathVariable("scheduleId") int scheduleId) throws ScheduleIdNotExistException {
        return scheduleService.getScheduleById(scheduleId);
    }

    @PutMapping(value = "/schedule")
    public ScheduleRequest updateScheduleDetails(@RequestBody ScheduleRequest scheduleRequest) throws ScheduleIdNotExistException {
        return scheduleService.updateScheduleDetails(scheduleRequest);
    }

    @DeleteMapping(value = "/schedule/{scheduleId}")
    public String deleteScheduleById(@PathVariable("scheduleId") int scheduleId) throws ScheduleIdNotExistException {
        return scheduleService.deleteScheduleById(scheduleId);
    }

}

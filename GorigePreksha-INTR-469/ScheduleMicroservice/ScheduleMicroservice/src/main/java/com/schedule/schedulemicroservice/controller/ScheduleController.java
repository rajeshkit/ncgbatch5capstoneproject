package com.schedule.schedulemicroservice.controller;

import com.schedule.schedulemicroservice.entity.NewScheduleRequest;
import com.schedule.schedulemicroservice.entity.Schedule;
import com.schedule.schedulemicroservice.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
@Slf4j
public class ScheduleController {

    //@Autowired
    ScheduleService scheduleService;

    ScheduleController(ScheduleService scheduleService){
        this.scheduleService=scheduleService;
    }

    @GetMapping("/getAllDetails")
    public Schedule getAllDetails(@RequestBody NewScheduleRequest newScheduleRequest){
        log.info("Searching the schedule details");
        return scheduleService.getAllDetails(newScheduleRequest);
    }
}

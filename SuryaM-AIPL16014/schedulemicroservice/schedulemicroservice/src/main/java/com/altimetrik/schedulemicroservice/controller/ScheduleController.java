package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public Schedule createNewSchedule(@RequestBody @Valid NewScheduleRequest newScheduleRequest) {
        return scheduleService.addSchedule(newScheduleRequest);
    }

    @GetMapping(value = "/schedule")
    public List<Schedule> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @GetMapping(value = "/schedule/{id}")
    public Schedule getScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistsException {
        return scheduleService.getScheduleById(scheduleId);
    }

    @PutMapping(value = "/schedule")
    public Schedule updateSchedule(@RequestBody @Valid Schedule schedule) throws ScheduleIdNotExistsException {
        return scheduleService.updateSchedule(schedule);
    }

    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistsException {
        return scheduleService.deleteScheduleById(scheduleId);
    }
}

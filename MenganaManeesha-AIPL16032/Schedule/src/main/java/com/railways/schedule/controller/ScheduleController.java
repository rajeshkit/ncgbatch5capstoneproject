package com.railways.schedule.controller;

import com.railways.schedule.exception.ScheduleNotFind;
import com.railways.schedule.model.NewScheduleResource;
import com.railways.schedule.model.Schedule;
import com.railways.schedule.service.ScheduleService;
import com.railways.train.exception.TrainNumberNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {

    private ScheduleService scheduleService;
    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public Schedule addSchedule(@RequestBody @Valid NewScheduleResource schedule) {
        return scheduleService.addSchedule(schedule);
    }
    @GetMapping("/schedule")
    public List<Schedule> getSchedule()
    {
        return scheduleService.getSchedule();
    }
    @GetMapping("schedule/{id}")
    public Schedule getScheduleById(@PathVariable("id") long scheduleId) throws ScheduleNotFind {
       return scheduleService.getScheduleById(scheduleId);

    }
    @PutMapping("/schedule")
    public Schedule updateSchedule(@RequestBody Schedule schedule) throws ScheduleNotFind {
        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping("/schedule/{id}")
    public String deleteSchedule(@PathVariable("id") long scheduleId) throws ScheduleNotFind {
        return scheduleService.deleteschedule(scheduleId);
    }

    @GetMapping("/train/{trainNumber}")
    public List<Schedule> getSchedulesByTrainNumber(@PathVariable Long trainNumber) throws TrainNumberNotFoundException {
      List<Schedule> scheduleList= scheduleService.getSchedulesByTrainNumber(trainNumber);
        if(scheduleList.isEmpty())
        {
            throw new TrainNumberNotFoundException("No train found");
        }
        return scheduleList;
    }
}

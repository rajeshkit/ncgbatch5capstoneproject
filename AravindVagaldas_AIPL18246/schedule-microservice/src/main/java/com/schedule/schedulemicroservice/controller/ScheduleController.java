package com.schedule.schedulemicroservice.controller;

import com.schedule.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;
import com.schedule.schedulemicroservice.service.ScheduleService;
import com.train.trainmicroservice.exception.TrainNumberNotExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/schedule")
    public Schedule addSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest)
    {
        return scheduleService.addSchedule(scheduleRequest);

    }

    @GetMapping("/schedule")
    public List<Schedule> getAllSchedules(){
        return scheduleService.getAllSchedules();
    }

    @GetMapping("schedule/{scheduleId}")
    public Schedule getScheduleById(@PathVariable("scheduleId") int scheduleId) throws ScheduleIdNotExistsException {
        return scheduleService.getScheduleById(scheduleId);

    }

    @PutMapping("/schedule")
    public Schedule updateSchedule(@RequestBody @Valid Schedule schedule){
        return scheduleService.updateSchedule(schedule);
    }

    @DeleteMapping("schedule/{scheduleId}")
    public String deleteScheduleById(@PathVariable("scheduleId") int scheduleId) throws ScheduleIdNotExistsException{
        return scheduleService.deleteScheduleByScheduleId(scheduleId);
    }

    @GetMapping("schedule/train/{trainNumber}")
    public List<Schedule> getScheduleByTrainNo(@PathVariable  int trainNumber) throws TrainNumberNotExistsException {
        return scheduleService.getScheduleByTrainNo(trainNumber);
    }

}

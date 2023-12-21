package com.schedule.schedulemicroservice.controller;

import com.schedule.schedulemicroservice.model.Schedule;
import com.schedule.schedulemicroservice.model.ScheduleRequest;
import com.schedule.schedulemicroservice.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping(value = "/schedule")
    public ResponseEntity<Schedule> addSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest)
    {
        Schedule schedule=scheduleService.addSchedule(scheduleRequest);
//        System.out.println(schedule.getScheduleId());
        if(schedule!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping(value="/schedule")
    public ResponseEntity<List<Schedule>> getAllSchedules(){
        List<Schedule> schedules=scheduleService.getAllSchedules();
        if(!schedules.isEmpty()){
            return ResponseEntity.ok(schedules);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping(value = "/schedule/{scheduleId}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable int scheduleId){
        Schedule schedule= scheduleService.getScheduleById(scheduleId);
        if(schedule!=null){
            return ResponseEntity.ok(schedule);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping(value = "/schedule/{scheduleId}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable int scheduleId,@Valid @RequestBody ScheduleRequest scheduleRequest){
        Schedule schedule=scheduleService.updateSchedule(scheduleId,scheduleRequest);
        if(schedule!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping(value = "/schedule/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int scheduleId){
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

}

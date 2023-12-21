package com.altimetrik.schedule.schedulerestapi.controller;

import com.altimetrik.schedule.schedulerestapi.exception.RouteIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.exception.ScheduleIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.exception.TrainIdNotFoundException;
import com.altimetrik.schedule.schedulerestapi.model.NewScheduleRequest;
import com.altimetrik.schedule.schedulerestapi.model.Schedule;
import com.altimetrik.schedule.schedulerestapi.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/newScheduleRequest")
    public ResponseEntity<Schedule> addScheduleRequest(@RequestBody NewScheduleRequest newScheduleRequest) throws TrainIdNotFoundException, RouteIdNotFoundException {
        return ResponseEntity.ok(scheduleService.addScheduleRequest(newScheduleRequest));
    }

    @GetMapping("/newScheduleRequest")
    public List<Schedule> getAllSchedule() {

        return scheduleService.getAllSchedule();
    }

    @GetMapping("/newScheduleRequest/{id}")
    public Schedule getScheduleById(@PathVariable("id") String scheduleId) throws ScheduleIdNotFoundException {
        return scheduleService.getScheduleById(scheduleId);
    }

    @PutMapping("/newScheduleRequest/{id}")
    public Schedule updateSchedule(@RequestBody NewScheduleRequest newScheduleRequest, @PathVariable("id") String scheduleId) throws ScheduleIdNotFoundException {
        return scheduleService.updateSchedule(newScheduleRequest,scheduleId);
    }

    @DeleteMapping("/newScheduleRequest/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String scheduleId) throws ScheduleIdNotFoundException {

        String msg = scheduleService.deleteById(scheduleId);
        return ResponseEntity.ok(msg);
    }

}

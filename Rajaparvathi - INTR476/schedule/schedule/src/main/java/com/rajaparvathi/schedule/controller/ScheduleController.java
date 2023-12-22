package com.rajaparvathi.schedule.controller;

import com.rajaparvathi.schedule.model.Schedule;
import com.rajaparvathi.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ScheduleController {
    // ScheduleRestController.java


        @Autowired
        private ScheduleService scheduleService;

        @PostMapping("/schedule")
        public ResponseEntity createSchedule(@RequestBody Schedule scheduleRequest) {
            Schedule schedule = scheduleService.createSchedule(scheduleRequest);
            return ResponseEntity.ok(schedule);

    }
}

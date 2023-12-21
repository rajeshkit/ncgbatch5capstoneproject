package com.schedulealtimetrik.schedulemicroservice.controller;

import com.schedulealtimetrik.schedulemicroservice.exception.ScheduleIdAlreadyExistException;
import com.schedulealtimetrik.schedulemicroservice.exception.ScheduleIdNotExistException;
import com.schedulealtimetrik.schedulemicroservice.model.NewScheduleRequest;
import com.schedulealtimetrik.schedulemicroservice.model.Schedule;
import com.schedulealtimetrik.schedulemicroservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {
        @Autowired
        private ScheduleService scheduleService;

        @PostMapping(value="/schedule")
        public Schedule addSchedule(@RequestBody @Valid NewScheduleRequest newScheduleRequest) throws ScheduleIdNotExistException, ScheduleIdAlreadyExistException {
                return scheduleService.addSchedule(newScheduleRequest);
        }

        @GetMapping(value = "/schedule")
        public List<Schedule> getAllSchedules() throws ScheduleIdNotExistException {
                return scheduleService.getAllSchedules();
        }

        @GetMapping(value = "/schedule/{id}")
        public Schedule getScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistException {
                return scheduleService.getScheduleById(scheduleId);
        }

        @PutMapping(value = "/schedule")
        public Schedule updateSchedule(@RequestBody @Valid Schedule schedule) throws ScheduleIdNotExistException {
                return scheduleService.updateSchedule(schedule);
        }

        @DeleteMapping(value = "/schedule/{id}")
        public String deleteScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistException {
                return scheduleService.deleteScheduleById(scheduleId);
        }



}

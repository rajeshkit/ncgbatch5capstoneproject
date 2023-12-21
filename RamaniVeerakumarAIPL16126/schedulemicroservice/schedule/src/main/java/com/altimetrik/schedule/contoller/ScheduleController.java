package com.altimetrik.schedule.contoller;

import com.altimetrik.schedule.exception.ScheduleIdNotExitsException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.model.ScheduleRequest;
import com.altimetrik.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @GetMapping("/{scheduleId}")
    public Schedule getScheduleById(@PathVariable int scheduleId) throws ScheduleIdNotExitsException {
        return scheduleService.getScheduleById(scheduleId);
    }
    @PostMapping(value = "/schedule")
    public Schedule addSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        return scheduleService.addSchedule(scheduleRequest);
    }
    @GetMapping(value = "/schedule")
    public List<Schedule> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }
    @PutMapping(value = "/schedule")
    public Schedule updateSchedule(@RequestBody Schedule schedule) throws ScheduleIdNotExitsException {
        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") int scheduleId) throws  ScheduleIdNotExitsException {
        return scheduleService.deleteScheduleById(scheduleId);
    }




































//    @PostMapping("/")
//    public Schedule createSchedule(@RequestBody ScheduleRequest ScheduleRequest) {
//        return scheduleService.createSchedule(ScheduleRequest);
//    }
}

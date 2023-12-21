package com.altimetrik.trainschedule.controller;
import com.altimetrik.trainschedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.trainschedule.exception.TrainNumberNotExistsException;
import com.altimetrik.trainschedule.model.Schedule;
import com.altimetrik.trainschedule.model.ScheduleRequest;
import com.altimetrik.trainschedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping(value = "/schedule")
    public Schedule addSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        return scheduleService.addSchedule(scheduleRequest);
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
    public ResponseEntity<?> updateSchedule(@RequestBody @Valid Schedule schedule, BindingResult result) throws TrainNumberNotExistsException {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Schedule updatedSchedule = scheduleService.updateSchedule(schedule);
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        } catch (ScheduleIdNotExistsException e) {
            return new ResponseEntity<>(" update unsuccessful with this ScheduleId" + schedule.getScheduleId() + " not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Throwing an exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") int scheduleId) throws ScheduleIdNotExistsException {
        return scheduleService.deleteScheduleById(scheduleId);
    }
}
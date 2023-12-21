package com.altimetrik.schedule.controller;
import com.altimetrik.schedule.exception.ScheduleNotExistsException;
import com.altimetrik.schedule.exception.TrainNotExistsException;
import com.altimetrik.schedule.model.Schedule;
import com.altimetrik.schedule.service.ScheduleService;
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
    public ResponseEntity<?> addSchedule(@Valid @RequestBody Schedule schedule, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Schedule addedSchedule = scheduleService.addSchedule(schedule);

            return new ResponseEntity<>(addedSchedule, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Throwing an exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/schedule")
    public List<Schedule> getAllSchedule() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping(value = "/schedule/{id}")
    public Schedule getScheduleById(@PathVariable("id") int scheduleId) throws ScheduleNotExistsException {
        return scheduleService.getScheduleById(scheduleId);
    }

    @PutMapping(value = "/schedule")
    public ResponseEntity<?> updateSchedule(@RequestBody @Valid Schedule schedule, BindingResult result) {
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
        } catch (ScheduleNotExistsException e) {
            return new ResponseEntity<>(" update unsuccessful with this ScheduleId" + schedule.getScheduleId() + " not found.", HttpStatus.NOT_FOUND);
        } catch (TrainNotExistsException e) {
            return new ResponseEntity<>("update unsuccessful with this TrainNo" + schedule.getTrainNo() + " not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Throwing an exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") int scheduleId) throws ScheduleNotExistsException {
        return scheduleService.deleteScheduleById(scheduleId);
    }
}
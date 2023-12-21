package com.altimetrik.trainschedule.controller;

import com.altimetrik.trainschedule.exception.RouteIdNotExistsException;
import com.altimetrik.trainschedule.exception.ScheduleIdNotExistsException;
import com.altimetrik.trainschedule.exception.TrainNoNotExistsException;
import com.altimetrik.trainschedule.model.Schedule;
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
        } catch (TrainNoNotExistsException e) {
            return new ResponseEntity<>("Cannot add schedule-api. Train with ID " + schedule.getTrainId() + " not found.", HttpStatus.NOT_FOUND);
        } catch (RouteIdNotExistsException e) {
            return new ResponseEntity<>("Cannot add schedule-api. Route with ID " + schedule.getRouteId() + " not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Throwing a exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/schedule")
    public List<Schedule> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @GetMapping(value = "/schedule/{id}")
    public Schedule getScheduleById(@PathVariable("id") String scheduleId) throws ScheduleIdNotExistsException {
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
        } catch (ScheduleIdNotExistsException e) {
            return new ResponseEntity<>("Couldnot update. Schedule with ID " + schedule.getScheduleId() + " not found.", HttpStatus.NOT_FOUND);
        } catch (TrainNoNotExistsException e) {
            return new ResponseEntity<>("Couldnot update. Train with ID " + schedule.getTrainId() + " not found.", HttpStatus.NOT_FOUND);
        } catch (RouteIdNotExistsException e) {
            return new ResponseEntity<>("Couldnot update. Route with ID " + schedule.getRouteId() + " not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Throwing a exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/schedule/{id}")
    public String deleteScheduleById(@PathVariable("id") String scheduleId) throws ScheduleIdNotExistsException {
        return scheduleService.deleteScheduleById(scheduleId);
    }
}

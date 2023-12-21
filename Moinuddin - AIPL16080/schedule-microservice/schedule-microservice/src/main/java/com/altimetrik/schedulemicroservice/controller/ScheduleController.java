package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.exceptions.RouteNotExistsException;
import com.altimetrik.schedulemicroservice.exceptions.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduleById(@PathVariable("id") String scheduleId) {
        try {
            // Deleting the schedule if it exists
            scheduleService.deleteScheduleById(scheduleId);
            // Returning a success message with a 200 OK status
            return new ResponseEntity<>("Schedule with ID " + scheduleId + " deleted successfully.", HttpStatus.OK);
        } catch (ScheduleIdNotExistsException e) {
            // Handling the case where the scheduleId does not exist
            return new ResponseEntity<>("Cannot delete. Schedule with ID " + scheduleId + " not found.", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping
    public ResponseEntity<?> updateSchedule(@RequestBody @Valid Schedule schedule, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, returning the error messages
            List<FieldError> errors = result.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            // Updating the schedule if it exists
            Schedule updatedSchedule = scheduleService.updateSchedule(schedule);

            // Returning the updated schedule with a 200 OK status
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        } catch (ScheduleIdNotExistsException e) {
            // Handling if the scheduleId does not exist
            return new ResponseEntity<>("Cannot update. Schedule with ID " + schedule.getScheduleId() + " not found.", HttpStatus.NOT_FOUND);
        }catch (TrainIdNotExistsException e){
            return new ResponseEntity<>("Cannot update. Train with ID " + schedule.getTrainNumber() + " not found.", HttpStatus.NOT_FOUND);
        } catch (RouteNotExistsException e){
        return new ResponseEntity<>("Cannot update. Route with ID " + schedule.getRouteId() + " not found.", HttpStatus.NOT_FOUND);
    }catch (Exception e){
            return new ResponseEntity<>("throwing general exception", HttpStatus.NOT_FOUND);

        }
    }
    @PostMapping
    public ResponseEntity<?> addSchedule(@Valid @RequestBody Schedule schedule, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, returning the error messages
            List<FieldError> errors = result.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        Schedule addedSchedule;
        try {
            // saving the schedule if validation passes
            addedSchedule = scheduleService.addSchedule(schedule);

            // Returning the updated schedule with a 200 OK status
//            return new ResponseEntity<>(addedSchedule, HttpStatus.OK);
            return new ResponseEntity<>(addedSchedule, HttpStatus.CREATED);
        } catch (ScheduleIdNotExistsException e) {
            // Handling if the scheduleId does not exist
            return new ResponseEntity<>("Cannot update. Schedule with ID " + schedule.getScheduleId() + " not found.", HttpStatus.NOT_FOUND);
        } catch (TrainIdNotExistsException e) {
            return new ResponseEntity<>("Cannot update. Train with ID " + schedule.getTrainNumber() + " not found.", HttpStatus.NOT_FOUND);
        } catch (RouteNotExistsException e) {
            return new ResponseEntity<>("Cannot update. Route with ID " + schedule.getRouteId() + " not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(" a general exception", HttpStatus.NOT_FOUND);
        }

        // Returning the created schedule with a 201 Created status

    }//-------------------------xxxxxxxxxxxxxxx--------------------------------

/*
-------------FORMAT-------------
{
    "scheduleId": "S0001",
    "departureTime": "2023-12-20T08:00:00",
    "arrivalTime": "2023-12-20T16:00:00",
    "sourceStation": "StationA",
    "destinationStation": "StationB",
    "trainNumber": "T0001"
}
-------------FORMAT-------------
*/

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();

        if (schedules.isEmpty()) {
            // Return 404 Not Found if no schedules are found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the list of schedules with 200 OK status
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable("id") String scheduleId) {
        try {
            // Retrieving the schedule if it exists
            Schedule schedule = scheduleService.getScheduleById(scheduleId);

            // Returning the schedule with a 200 OK status
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (ScheduleIdNotExistsException e) {
            // Handling the case where the scheduleId does not exist
            return new ResponseEntity<>("Schedule with ID " + scheduleId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

}

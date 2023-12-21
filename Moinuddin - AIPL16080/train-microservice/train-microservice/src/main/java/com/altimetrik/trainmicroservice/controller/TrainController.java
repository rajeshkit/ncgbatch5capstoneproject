package com.altimetrik.trainmicroservice.controller;

import com.altimetrik.trainmicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainById(@PathVariable("id") String trainId) {
        try {
            // Deleting the train if it exists
            trainService.deleteTrainByNumber(trainId);
            // Returning a success message with a 200 OK status
            return new ResponseEntity<>("Train with ID " + trainId + " deleted successfully.", HttpStatus.OK);
        } catch (TrainIdNotExistsException e) {
            // Handling the case where the trainId does not exist
            return new ResponseEntity<>("Cannot delete. Train with ID " + trainId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTrain(@RequestBody @Valid Train train, BindingResult result) {
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
            // Updating the train if it exists
            Train updatedTrain = trainService.updateTrain(train);

            // Returning the updated train with a 200 OK status
            return new ResponseEntity<>(updatedTrain, HttpStatus.OK);
        } catch (TrainIdNotExistsException e) {
            // Handling if the trainNumber does not exist
            return new ResponseEntity<>("Cannot update. Train with trainNumber " + train.getTrainNumber() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addTrain(@Valid @RequestBody Train train, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, returning the error messages
            List<FieldError> errors = result.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        // saving the train if validation passes
        Train addedTrain = trainService.addTrain(train);

        // Returning the created train with a 201 Created status
        return new ResponseEntity<>(addedTrain, HttpStatus.CREATED);
    }

    /*
    -------------FORMAT-------------
    {
        "trainNumber": "T0001",
        "trainName": "Bengaluru Express",
        "numberOfACCoaches": 5,
        "acCoachTotalSeats": 50,
        "numberOfGeneralCoaches": 8,
        "generalCoachTotalSeats": 100,
        "numberOfSleeperCoaches": 10,
        "sleeperCoachTotalSeats": 120,
        "totalDistance": 500
    }
    -------------FORMAT-------------
    */

    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains() {
        List<Train> trains = trainService.getAllTrains();

        if (trains.isEmpty()) {
            // Return 404 Not Found if no routes are found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the list of routes with 200 OK status
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainById(@PathVariable("id") String trainId) {
        try {
            // Retrieving the train if it exists
            Train train = trainService.getTrainByNumber(trainId);

            // Returning the train with a 200 OK status
            return new ResponseEntity<>(train, HttpStatus.OK);
        } catch (TrainIdNotExistsException e) {
            // Handling the case where the trainId does not exist
            return new ResponseEntity<>("Train with ID " + trainId + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}

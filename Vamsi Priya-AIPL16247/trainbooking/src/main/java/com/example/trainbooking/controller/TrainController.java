package com.example.trainbooking.controller;

import com.example.trainbooking.requestdto.TracklineDto;
import com.example.trainbooking.responsedto.TracklineResponse;
import com.example.trainbooking.service.ITracklineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trackline-api")
public class TrainController {

    private final ITracklineService tracklineService;
    @Autowired
    public TrainController(ITracklineService tracklineService){
        this.tracklineService=tracklineService;
    }
        @PostMapping("/register")
        public ResponseEntity<String> registerTrackline(@RequestBody TracklineDto tracklineDTO)
        {
            String message = tracklineService.registerTrackline(tracklineDTO);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        @GetMapping("/get-byId/{id}")
        public ResponseEntity<TracklineResponse> getTracklineById(@PathVariable int id)
        {
            TracklineResponse tracklineResponse = tracklineService.getTracklineById(id);
            return new ResponseEntity<>(tracklineResponse, HttpStatus.FOUND);
        }

        @PutMapping("/update")
        public ResponseEntity<String> updateTrackline(@RequestParam("id") int id , @RequestBody TracklineDto tracklineDTO)
        {
            String message = tracklineService.updateTrackline(id, tracklineDTO);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
    }
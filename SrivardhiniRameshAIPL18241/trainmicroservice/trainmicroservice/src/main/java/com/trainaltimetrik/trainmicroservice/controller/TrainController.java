package com.trainaltimetrik.trainmicroservice.controller;

import com.trainaltimetrik.trainmicroservice.exception.TrainNumberAlreadyExistException;
import com.trainaltimetrik.trainmicroservice.exception.TrainNumberNotExistException;
import com.trainaltimetrik.trainmicroservice.model.Train;
import com.trainaltimetrik.trainmicroservice.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Controller
@RestController
@RequestMapping("/train-api")
public class TrainController {

        @Autowired
        private TrainService trainService;

        @PostMapping(value="/train")
        public Train addTrain(@RequestBody @Valid Train train) throws TrainNumberAlreadyExistException, TrainNumberNotExistException {
            return trainService.addTrain(train);
        }

        @GetMapping(value = "/train")
        public List<Train> getAllTrain()throws TrainNumberNotExistException{
            return trainService.getAllTrain();
        }

        @GetMapping(value = "/train/{number}")
        public Train getTrainByNumber(@PathVariable("number") int trainNumber)throws TrainNumberNotExistException{
            return trainService.getTrainByNumber(trainNumber);
        }

        @PutMapping(value = "/train")
        public Train updateTrain(@RequestBody Train train)throws TrainNumberNotExistException{
            return trainService.updateTrain(train);
        }

        @DeleteMapping(value = "/train/{number}")
        public String deleteTrainById(@PathVariable("number") int trainNumber)throws TrainNumberNotExistException{
            return trainService.deleteTrainByNumber(trainNumber);
        }

}

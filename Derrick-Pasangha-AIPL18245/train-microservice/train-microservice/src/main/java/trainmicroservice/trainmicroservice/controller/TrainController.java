package trainmicroservice.trainmicroservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trainmicroservice.trainmicroservice.model.Train;
import trainmicroservice.trainmicroservice.service.TrainService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/train-api")

public class TrainController {
    private static final Logger logger = LoggerFactory.getLogger("logback");
    @Autowired
    private TrainService trainService;
    @PostMapping(value="/train")
    public Train addTrain(@RequestBody @Valid Train train){
        logger.info("Taking train table values");
        return trainService.addTrain(train);
    }
    @GetMapping(value="/train")
    public List<Train> getAllTrains(){
        logger.info("Getting all train objects")
        ;return trainService.getAllTrains();
    }
    @GetMapping(value="/train/{id}")
    public Optional<Train> getTrainById(@PathVariable("id") int trainNo){
        return trainService.getTrainByNo(trainNo);
    }
    @PutMapping(value="/train")
    public Train updateTrain(@RequestBody @Valid Train train){
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value="/train/{id}")
    public String deleteTrainByNo(@PathVariable("id") int trainNo){
        return trainService.deleteTrainByNo(trainNo);
    }
}

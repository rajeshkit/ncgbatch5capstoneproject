package com.train.trainmicroservice.controller;

import com.train.trainmicroservice.entity.Train;
import com.train.trainmicroservice.service.TrainService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("train")
@Slf4j
public class TrainController {

  //  @Autowired

   private TrainService trainService;

   TrainController(TrainService trainService1){
       this.trainService=trainService1;
   }


    @PostMapping("/addTrain")
    public Train addTrain(@Valid  @RequestBody Train train){
        log.info("adding the Train Object");
        return trainService.addTrain(train);
    }


    @GetMapping("/findTrain")
    public Train findTrain(@RequestParam int trainNumber){
        log.info("entered into findTrain");
        return trainService.findTrain(trainNumber);
    }
}

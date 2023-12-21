package com.train.trainmicroservice.service;

import com.train.trainmicroservice.exceptions.TrainNotFoundException;
import com.train.trainmicroservice.repository.TrainRepository;
import com.train.trainmicroservice.entity.Train;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TrainService {

    TrainRepository trainRepository;

    //@Autowired
    TrainService(TrainRepository trainRepository){
        this.trainRepository=trainRepository;
    }

    public Train addTrain(Train train) {
        log.info("processing the train object into the Database");
        return trainRepository.save(train);
    }

    public Train findTrain(int trainNumber) {
       log.info("finding the train using its trainNumber "+trainNumber);
      Train train = trainRepository.findByTrainNumber(trainNumber);
      if(train==null){
          throw new TrainNotFoundException("Train Not Found With Given Id "+ trainNumber);
      }
      return train;
    }
}

package com.altimetrik.train.service;

import com.altimetrik.train.exception.TrainIdNotExistsException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TrainServiceImp implements TrainService {
    private static final Logger logger = LoggerFactory.getLogger(TrainServiceImp.class);

    @Autowired
    private TrainRepository trainRepository;


    public Train getTrainByNumber(int trainNumber) throws TrainIdNotExistsException {
        logger.info("Getting train by number: {}", trainNumber);
        Optional<Train> pro = trainRepository.findById(trainNumber);

        if (pro.isEmpty()) {
            logger.warn("Train with train number {} not found", trainNumber);
            throw new TrainIdNotExistsException("Train with this train number not found");
        }

        Train train = pro.get();
        logger.info("Found train: {}", train);
        return train;
    }

    @Override
    public Train addTrain(Train train) {
        logger.info("Adding new train: {}", train);
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        logger.info("Getting all trains");
        List<Train> allTrain=trainRepository.findAll();
        if (allTrain.isEmpty()){
            logger.warn("No trains available");
            throw new NoSuchElementException("there is no trains available");
        }
        logger.info("Found {} trains", allTrain.size());
        return allTrain;
    }

    @Override
    public Train updateTrain(@Validated Train train) throws TrainIdNotExistsException {
        logger.info("Updating train with number: {}", train.getTrainNumber());
        Train exitingTrain=trainRepository.findById(train.getTrainNumber()).orElse(null);
        if(exitingTrain==null){
            logger.warn("Train with train number {} not found for update", train.getTrainNumber());
            throw new TrainIdNotExistsException("train number not found:"+train.getTrainNumber());
        }
        Train train1=trainRepository.save(train);

        logger.info("Train updated successfully: {}",train1);
        return train1;
    }

    @Override
    public String deleteTrainByNumber(int trainNumber) throws TrainIdNotExistsException {
        logger.info("Deleting train with number: {}", trainNumber);
        String message = "Train Does not exists to delete";
        Train t = getTrainByNumber(trainNumber);
        if (t != null) {
            trainRepository.deleteById(trainNumber);
            message = "Train deleted successfully";
            logger.info("Train deleted successfully with number: {}", trainNumber);
            return message;
        }
        logger.warn("Train with number {} not found for deletion", trainNumber);
        return message;
    }


}

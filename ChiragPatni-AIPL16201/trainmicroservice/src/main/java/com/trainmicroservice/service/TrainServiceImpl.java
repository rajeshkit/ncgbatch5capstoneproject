package com.trainmicroservice.service;

import com.trainmicroservice.exception.TrainNumberDoesNotExistException;
import com.trainmicroservice.model.Train;
import com.trainmicroservice.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService{
    @Autowired
    private TrainRepository trainRepository;
    private static final Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);
    @Override
    public Train saveTrainDetails(Train train) {
        logger.info("Saving train details : {}", train);
        return trainRepository.save(train);
    }
    @Override
    public List<Train> getAllTrainsDetail() {
        logger.info("Fetching all trains.");
        return trainRepository.findAll();
    }
    @Override
    public Train getTrainDetailsById(int trainNumber) throws TrainNumberDoesNotExistException {
        Optional<Train> trainOptional = trainRepository.findById(trainNumber);
        if (trainOptional.isEmpty()){
            logger.error("TrainNumberDoesNotExistException occurred for Train-Number : {}", trainNumber);
            throw new TrainNumberDoesNotExistException("The Train with the provided Train-Number does not exist, Try again.");
        }
        logger.info("Fetching train by Train-Number : {}", trainNumber);
        return trainOptional.get();
    }
    @Override
    public Train updateTrainDetails(Train train) throws TrainNumberDoesNotExistException {
        if(getTrainDetailsById(train.getTrainNumber())!=null) {
            logger.info("Updating train details: {}", train);
            return trainRepository.save(train);
        }
        return null;
    }
    @Override
    public String deleteTrainDetails(int trainNumber) throws TrainNumberDoesNotExistException {
        String message="Train does not exists for the given Train-ID";
        Train train = getTrainDetailsById(trainNumber);
        if(train!=null){
            logger.info("Deleting train details with ID: {}", trainNumber);
            trainRepository.deleteById(trainNumber);
            message="Train deleted successfully";
            return message;
        }
        return message;
    }
}

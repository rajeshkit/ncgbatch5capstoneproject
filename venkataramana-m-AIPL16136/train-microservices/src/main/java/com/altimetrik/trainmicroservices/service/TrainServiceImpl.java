package com.altimetrik.trainmicroservices.service;

import com.altimetrik.trainmicroservices.exception.TrainNumberAlreadyExistsException;
import com.altimetrik.trainmicroservices.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservices.model.Train;
import com.altimetrik.trainmicroservices.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService {

    private static final Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrain(Train train) throws TrainNumberAlreadyExistsException {
        logger.info("Adding new train: {}", train);
        if (trainRepository.existsById(train.getTrainNumber())) {
            throw new TrainNumberAlreadyExistsException("Train with ID " + train.getTrainNumber() + " already exists.");
        }
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        logger.info("Fetching all trains");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByTrainNumber(long trainNumber) throws TrainNumberNotExistsException {
        logger.info("Fetching train by train number: {}", trainNumber);
        Optional<Train> train = trainRepository.findById(trainNumber);
        if (train.isEmpty()) {
            throw new TrainNumberNotExistsException("TrainNumber does not exist");
        } else {
            return train.get();
        }
    }

    @Override
    public Train updateTrain(Train train) throws TrainNumberNotExistsException {
        logger.info("Updating train: {}", train);
        Train existingTrain = getTrainByTrainNumber(train.getTrainNumber());
        if (existingTrain != null) {
            return trainRepository.save(train);
        } else {
            throw new TrainNumberNotExistsException("Train doesn't exist");
        }
    }

    @Override
    public String deleteTrainByTrainNumber(long trainNumber) throws TrainNumberNotExistsException {
        logger.info("Deleting train by train number: {}", trainNumber);
        Train train = getTrainByTrainNumber(trainNumber);
        if (train != null) {
            trainRepository.deleteById(trainNumber);
            logger.info("Train deleted successfully");
            return "Train deleted successfully";
        } else {
            throw new TrainNumberNotExistsException("Train doesn't exist");
        }
    }

}

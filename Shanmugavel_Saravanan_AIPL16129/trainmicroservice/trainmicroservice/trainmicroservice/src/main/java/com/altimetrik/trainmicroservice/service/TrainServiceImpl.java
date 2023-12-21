package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainNumberAlreadyExists;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotFoundException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Autowired
    TrainRepository trainRepository;

    @Override
    public Train addTrain(Train train) throws TrainNumberAlreadyExists {
        if (trainRepository.existsById(train.getTrainNumber())) {
            throw new TrainNumberAlreadyExists("Train with ID " + train.getTrainNumber() + " already exists.");
        }
        LOGGER.info("Adding a new train: {}", train);
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrain()
    {
        LOGGER.info("Fetching all trains");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByNumber(int trainNumber) throws TrainNumberNotFoundException {
        LOGGER.info("Fetching train by number: {}", trainNumber);
        Optional<Train> tr = trainRepository.findById(trainNumber);
        if (tr.isEmpty()) {
            LOGGER.warn("Train with number {} not found in the database", trainNumber);
            throw new TrainNumberNotFoundException("Train Number does not exist in the database. Check the train ID.");
        }
        return tr.get();
    }

    @Override
    public Train updateTrain(Train train) throws TrainNumberNotFoundException {
        LOGGER.info("Updating train: {}", train);
        if (getTrainByNumber(train.getTrainNumber()) != null) {
            return trainRepository.save(train);
        } else {
            LOGGER.warn("Train with number {} not found. Update failed.", train.getTrainNumber());
            return null;
        }
    }

    @Override
    public String deleteTrainByNumber(int trainNumber) throws TrainNumberNotFoundException {
        LOGGER.info("Deleting train with number: {}", trainNumber);
        String message = "Train Number does not exist to delete";
        Train t = getTrainByNumber(trainNumber);
        if (t != null) {
            trainRepository.deleteById(trainNumber);
            message = "Train deleted successfully";
            LOGGER.info("Train with number {} deleted successfully", trainNumber);
            return message;
        }
        return message;
    }
}

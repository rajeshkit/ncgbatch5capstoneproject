package com.altimetrik.trainbooking.service;

import com.altimetrik.trainbooking.exception.TrainNumberNotExistsException;
import com.altimetrik.trainbooking.model.Train;
import com.altimetrik.trainbooking.repository.TrainRepository;
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
    public Train addTrain(Train train) {
        logger.info("Adding a new train: {}", train);
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAlltrain() {
        logger.info("Fetching all trains");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByNumber(int trainNumber) throws TrainNumberNotExistsException {
        logger.info("Fetching train by number: {}", trainNumber);
        Optional<Train> optionalTrain = trainRepository.findById(trainNumber);

        if (optionalTrain.isEmpty()) {
            logger.warn("Train with number {} not found", trainNumber);
            throw new TrainNumberNotExistsException("This Train doesn't exist");
        }

        return optionalTrain.get();
    }

    @Override
    public Train updateTrain(Train train) throws TrainNumberNotExistsException {
        int trainNumber = train.getTrainNumber();
        logger.info("Updating train with number: {}", trainNumber);

        if (getTrainByNumber(trainNumber) != null) {
            logger.info("Train found. Updating information.");
            return trainRepository.save(train);
        }

        logger.warn("Train with number {} not found. Update failed.", trainNumber);
        return null;
    }

    @Override
    public String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistsException {
        logger.info("Deleting train with number: {}", trainNumber);
        String message = "This train doesn't exist";
        Train train = getTrainByNumber(trainNumber);

        if (train != null) {
            trainRepository.deleteById(trainNumber);
            message = "Train deleted successfully";
            logger.info("Train with number {} deleted successfully", trainNumber);
            return message;
        }

        logger.warn("Train with number {} not found. Deletion failed.", trainNumber);
        return message;
    }
}

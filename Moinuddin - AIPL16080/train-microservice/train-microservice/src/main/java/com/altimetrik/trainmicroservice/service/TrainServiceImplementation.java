package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImplementation implements TrainService {

    private static final Logger logger = LoggerFactory.getLogger(TrainServiceImplementation.class);

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrain(Train train) {
        logger.info("Adding train: {}", train);
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        logger.info("Fetching all trains");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByNumber(String trainNumber) throws TrainIdNotExistsException {
        logger.info("Fetching train by number: {}", trainNumber);
        Optional<Train> trainOptional = trainRepository.findById(trainNumber);

        if (trainOptional.isEmpty()) {
            throw new TrainIdNotExistsException("Train number does not exist in the database. Check the train number: " + trainNumber);
        }

        return trainOptional.get();
    }

    @Override
    public Train updateTrain(Train train) throws TrainIdNotExistsException {
        logger.info("Updating train: {}", train);
        String trainNumber = train.getTrainNumber();
        if (trainRepository.existsById(trainNumber)) {
            return trainRepository.save(train);
        } else {
            throw new TrainIdNotExistsException("Train with number " + trainNumber + " does not exist in the database.");
        }
    }

    @Override
    public String deleteTrainByNumber(String trainNumber) throws TrainIdNotExistsException {
        logger.info("Deleting train by number: {}", trainNumber);
        Train train = getTrainByNumber(trainNumber);
        trainRepository.deleteById(trainNumber);
        return "Train deleted successfully";
    }
}
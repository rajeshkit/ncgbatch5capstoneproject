package com.trainaltimetrik.trainmicroservice.service;

import com.trainaltimetrik.trainmicroservice.exception.TrainNumberAlreadyExistException;
import com.trainaltimetrik.trainmicroservice.exception.TrainNumberNotExistException;
import com.trainaltimetrik.trainmicroservice.model.Train;
import com.trainaltimetrik.trainmicroservice.repository.TrainRepository;
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
    public Train addTrain(Train train) throws TrainNumberAlreadyExistException {
        logger.info("Adding a new train: {}", train);
        if (trainRepository.existsById(train.getTrainNumber())) {
            throw new TrainNumberAlreadyExistException("Train with ID " + train.getTrainNumber() + " already exists.");
        }
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrain() {
        logger.info("Getting all trains");
        List<Train> allTrains = trainRepository.findAll();
        if (allTrains != null && !allTrains.isEmpty()) {
            logger.info("Found {} trains", allTrains.size());
        } else {
            logger.error("No trains found");
        }
        return allTrains;
    }

    @Override
    public Train getTrainByNumber(int trainNumber) throws TrainNumberNotExistException {
        logger.info("Getting train by number: {}", trainNumber);
        Optional<Train> trainOptional = trainRepository.findById(trainNumber);
        Train train = trainOptional.orElseThrow(() ->
                new TrainNumberNotExistException("Train Number Does Not Exist In The Database!!!! Check The Train Number"));
        logger.info("Found train by number {}: {}", trainNumber, train);
        return train;
    }

    @Override
    public Train updateTrain(Train train) throws TrainNumberNotExistException {
        logger.info("Updating train: {}", train);
        Train updatedTrain = getTrainByNumber(train.getTrainNumber());
        if (updatedTrain != null) {
            updatedTrain = trainRepository.save(train);
            logger.info("Train updated successfully: {}", updatedTrain);
        } else {
            logger.error("Error updating train: {}", train);
        }
        return updatedTrain;
    }

    @Override
    public String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistException {
        logger.info("Deleting train by number: {..}", trainNumber);
        String message = "Train Number Does Not Exist In The Database!!!! Check The Train Number";
        Train t = getTrainByNumber(trainNumber);
        if (t != null) {
            trainRepository.deleteById(trainNumber);
            logger.info("Train {} has been deleted", trainNumber);
            return "Train Has Been Deleted " + trainNumber;
        } else {
            logger.error("Train deletion unsuccessful: {}", trainNumber);
            return message;
        }
    }

}

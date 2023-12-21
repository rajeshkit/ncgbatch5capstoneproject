package com.train.trainmicroservice.service;

import com.train.trainmicroservice.exception.TrainAlreadyExistsException;
import com.train.trainmicroservice.exception.TrainNotFoundException;
import com.train.trainmicroservice.exception.TrainOperationException;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.repository.TrainRepository;
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
        logger.info("Adding a new train...");

        if (trainRepository.existsById(train.getTrainNumber())) {
            logger.error("Train Number {} already exists. Please try with another number.", train.getTrainNumber());
            throw new TrainAlreadyExistsException("Train Number " + train.getTrainNumber() + " already exists. Please try with another number.");
        }

        logger.info("Saving train to the database.");
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        logger.info("Getting all trains...");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByTrainNo(int trainNumber) {
        logger.info("Getting train by Train Number: {}", trainNumber);

        Optional<Train> train = trainRepository.findById(trainNumber);
        return train.orElseThrow(() -> new TrainNotFoundException("Train not found with ID: " + trainNumber));
    }

    @Override
    public Train updateTrain(Train train) {
        logger.info("Updating train with Train Number: {}", train.getTrainNumber());

        if (getTrainByTrainNo(train.getTrainNumber()) == null) {
            logger.error("The Train with Train Number {} doesn't exist.", train.getTrainNumber());
            throw new TrainNotFoundException("The Train with this ID doesn't exist.");
        }

        logger.info("Saving updated train to the database.");
        return trainRepository.save(train);
    }

    @Override
    public void deleteTrainByTrainNo(int trainNumber) {
        logger.info("Deleting train with Train Number: {}", trainNumber);

        if (getTrainByTrainNo(trainNumber) == null) {
            logger.error("Train Number {} not found. Deletion failed.", trainNumber);
            throw new TrainOperationException("Train Number " + trainNumber + " not found. Deletion Failed.");
        }

        logger.info("Deleting train from the database.");
        trainRepository.deleteById(trainNumber);
    }
}

package com.altimetrik.trainbookingsystem.service;

import com.altimetrik.trainbookingsystem.exception.TrainNoNotExistsException;
import com.altimetrik.trainbookingsystem.model.Train;
import com.altimetrik.trainbookingsystem.respository.TrainRepository;
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
        logger.info("Adding new train: {}", train);
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrain() {
        logger.info("Fetching all trains");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByNo(String trainNo) throws TrainNoNotExistsException {
        Optional<Train> pro = trainRepository.findById(trainNo);

        if (pro.isEmpty()) {
            String errorMessage = "Train is not exists in the db!!! check the Train No: " + trainNo;
            logger.error(errorMessage);
            throw new TrainNoNotExistsException(errorMessage);
        }

        return pro.get();
    }

    @Override
    public Train updateTrain(Train train) throws TrainNoNotExistsException {
        if (getTrainByNo(train.getTrainNo()) != null) {
            logger.info("Updating train: {}", train);
            return trainRepository.save(train);
        } else {
            String errorMessage = "Train does not exist for updating: " + train.getTrainNo();
            logger.error(errorMessage);
            throw new TrainNoNotExistsException(errorMessage);
        }
    }

    @Override
    public String deleteTrainByNo(String trainNo) throws TrainNoNotExistsException {
        String message = "Train does not exist to delete";
        Train t = getTrainByNo(trainNo);
        if (t != null) {
            logger.info("Deleting train with Train No: {}", trainNo);
            trainRepository.deleteById(trainNo);
            message = "Train deleted successfully";
        } else {
            logger.error("Train does not exist for deletion: {}", trainNo);
        }
        return message;
    }
}

package com.altimetrik.trainbookingsystem.service;

import com.altimetrik.trainbookingsystem.exception.TrainNotExistsException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrain(Train train) {
        LOGGER.info("Adding new train: {}", train);
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAlltrain() {
        LOGGER.info("all trains fetching");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByNo(int trainNo) throws TrainNotExistsException {
        LOGGER.info("Fetching train by trainNo: {}", trainNo);
        Optional<Train> pro = trainRepository.findById(trainNo);

        if (pro.isEmpty()) {
            LOGGER.warn("Train not found with this trainNo: {}", trainNo);
            throw new TrainNotExistsException("Train not exists in the database");
        }

        return pro.get();
    }

    @Override
    public Train updateTrain(Train train) throws TrainNotExistsException {
        LOGGER.info("Updating train: {}", train);
        if (getTrainByNo(train.getTrainNo()) != null) {
            return trainRepository.save(train);
        }
        return null;
    }

    @Override
    public String deleteTrainByNo(int trainNo) throws TrainNotExistsException {
        LOGGER.info("Deleting train with the trainNo: {}", trainNo);
        String message = "TrainNo Does not exists to delete";
        Train p = getTrainByNo(trainNo);
        if (p != null) {
            trainRepository.deleteById(trainNo);
            message = "Train deleted successfully";
            return message;
        }
        return message;
    }
}
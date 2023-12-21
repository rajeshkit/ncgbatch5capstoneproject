package com.train.trainmicroservice.service;

import com.train.trainmicroservice.exception.TrainAlreadyExistsException;
import com.train.trainmicroservice.exception.TrainOperationException;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.repository.TrainRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.train.trainmicroservice.exception.TrainNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrain(Train train) {
        if (trainRepository.existsById(train.getTrainNumber())) {
            throw new TrainAlreadyExistsException("Train Number " + train.getTrainNumber() + " already exists..! Please try with other number..!");
        }
        log.info("inserting train into database");
        return trainRepository.save(train);

    }

    @Override
    public List<Train> getAllTrains() {
        log.info("fetching all trains from database");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByTrainNo(int trainNumber) {
        Optional<Train> train = trainRepository.findById(trainNumber);
        log.info("fetching train by id from database");
        return train.orElseThrow(() -> new TrainNotFoundException("Train not found with ID: " + trainNumber));
    }

    @Override
    public Train updateTrain(Train train) {
        log.info("updating train in database");
        return trainRepository.save(train);
    }

    @Override
    public void deleteTrainByTrainNo(int trainNumber) {
        if (!trainRepository.existsById(trainNumber)) {
            throw new TrainOperationException("Train Number " + trainNumber + " not found.Deletion Failed.!!");
        }
        log.info("deleting train by id from database");
        trainRepository.deleteById(trainNumber);
    }
}

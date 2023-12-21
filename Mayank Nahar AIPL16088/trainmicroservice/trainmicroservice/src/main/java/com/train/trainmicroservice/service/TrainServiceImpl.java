package com.train.trainmicroservice.service;

import com.train.trainmicroservice.exception.TrainIdNotExistException;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.repository.TrainRepository;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrain(Train train) {
        log.info("Getting train object");
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrain() {
        log.info("Train Details Fetch Succesfully.and Return to Controller");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainById(int trainNumber) throws TrainIdNotExistException {
        Optional<Train> byId = trainRepository.findById(trainNumber);
        if (byId.isEmpty()) {
            throw new TrainIdNotExistException("Train Id not exist in database check the Train id");
        }

        return byId.get();
    }

    @Override
    public Train updateTrain(Train train) throws TrainIdNotExistException {
        if (getTrainById(train.getTrainNumber()) != null) {
            log.info("Train Update Succesfully");
            return trainRepository.save(train);
        }
        return null;
    }

    @Override
    public String deleteTrainById(int trainId) throws TrainIdNotExistException {
        String msg = "Train Not Exist";
        Train t = getTrainById(trainId);
        if (t != null) {
            trainRepository.deleteById(trainId);
            msg = "Train Deleted";
            log.info("Train Deleted Succesfully");
            return msg;
        }
        return msg;
    }
}

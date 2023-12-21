package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TrainServiceImpl implements TrainService{
    private static final Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);
    @Autowired
    private TrainRepository trainRepository;
    @Override
    public Train addTrain(Train train) {
        logger.info("Adding new train: {}", train);
        trainRepository.save(train);
        logger.info("Train added successfully: {}", train);
        return train;
    }

    @Override
    public List<Train> getAllTrain() {
        logger.info("Getting all trains");
        List<Train> trains =trainRepository.findAll();
        logger.info("Returning {} trains", trains.size());
        return trains;
    }

    @Override
    public Train getTrainById(int trainId) throws TrainIdNotExistsException{
        logger.info("Getting train by ID: {}", trainId);
        Optional<Train> tr=trainRepository.findById(trainId);
        if(tr.isEmpty()){
            logger.error("TrainID {} does not exist", trainId);
            throw new TrainIdNotExistsException("Train does not exists in the db!!");
        }
        Train train = tr.get();
        logger.info("Returning train: {}", train);
        return train;
    }

    @Override
    public Train updateTrain(Train train) throws TrainIdNotExistsException{
        if(getTrainById(train.getTrainId())!=null) {
            Train updatedTrain=trainRepository.save(train);
            logger.info("Train updated successfully: {}", updatedTrain);
            return updatedTrain;
        }
        return null;
    }

    @Override
    public String deleteTrainById(int trainId) throws TrainIdNotExistsException{
        logger.info("Deleting train by ID: {}", trainId);
        String message="Train does not Exist";
        if(getTrainById(trainId)!=null){
            trainRepository.deleteById(trainId);
            message="Train deleted successfully";
            logger.info(message);
        }else {
            logger.error("TrainId {} does not exist", trainId);
        }
        return message;
    }
}

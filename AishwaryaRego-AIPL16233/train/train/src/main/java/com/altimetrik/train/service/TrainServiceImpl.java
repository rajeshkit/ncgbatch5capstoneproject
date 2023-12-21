package com.altimetrik.train.service;

import com.altimetrik.train.exception.TrainAlreadyExistsException;
import com.altimetrik.train.exception.TrainNotExistsException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.repository.TrainRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainServiceImpl implements TrainService {

    private static final Logger LOGGER = Logger.getLogger(TrainServiceImpl.class.getName());

    @Autowired
    private TrainRepository trainRepository;

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public List<Train> getAllTrains() {
        LOGGER.log(Level.INFO, "Fetching all trains");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByNum(int trainNum) throws TrainNotExistsException {
        LOGGER.log(Level.INFO, "Fetching train by number: {0}", trainNum);
        Optional<Train> tr = trainRepository.findById(trainNum);
        if (tr.isEmpty()) {
            LOGGER.log(Level.WARNING, "Train with number {0} does not exist", trainNum);
            throw new TrainNotExistsException("The train with this id does not exist");
        }
        return tr.get();
    }

    @Override
    public Train addTrain(Train train) throws TrainAlreadyExistsException {
        LOGGER.log(Level.INFO, "Adding new train: {0}", train);

        // Check if the train with the same number already exists
        if (trainRepository.existsById(train.getTrainNum())) {
            throw new TrainAlreadyExistsException("Train with number " + train.getTrainNum() + " already exists.");
        }

        return trainRepository.save(train);
    }

    @Override
    public Train updateTrain(Train train) throws TrainNotExistsException {
        LOGGER.log(Level.INFO, "Updating train: {0}", train);
        if (getTrainByNum(train.getTrainNum()) != null) {
            return trainRepository.save(train);
        }
        LOGGER.log(Level.WARNING, "Train with number {0} does not exist. Update failed.", train.getTrainNum());
        return null;
    }

    @Override
    public String deleteTrain(int trainNum) throws TrainNotExistsException {
        LOGGER.log(Level.INFO, "Deleting train with number: {0}", trainNum);
        String message = "Train does not exist to delete";
        Train t = getTrainByNum(trainNum);
        if (t != null) {
            trainRepository.deleteById(trainNum);
            message = "Train deleted successfully";
            return message;
        }
        LOGGER.log(Level.WARNING, "Train with number {0} does not exist. Deletion failed.", trainNum);
        return message;
    }
}

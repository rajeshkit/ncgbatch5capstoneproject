package com.altimetrik.train.service;

import java.util.Optional;
import com.altimetrik.train.exception.TrainNumberNotExistException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.repository.TrainRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Log
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;


    @Override
    public Train addTrain(Train train) {
        System.out.println("Train Added Successfully...");
        log.info("Inserting Train docs with trainNumber: "+train.getTrainNumber());
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        System.out.println("Getting all Trains...");
        log.info("Fetching all Train docs.");
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByTrainNumber(int trainNumber) throws TrainNumberNotExistException {
        Optional<Train> tr = trainRepository.findById(trainNumber);
        if(tr.isPresent()){
            System.out.println("Getting train with trainNumber");
            log.info("Fetching train docs with trainNumber: "+trainNumber);
            return tr.get();
        } else{
            throw new TrainNumberNotExistException("Train Number Not Exist!");
        }

    }

    @Override
    public Train updateTrain(Train train) throws TrainNumberNotExistException {
        if(getTrainByTrainNumber(train.getTrainNumber())!=null){
            System.out.println("Train Details Updated Successfully");
            log.info("Updating train docs with trainNumber: "+train.getTrainNumber());
            return trainRepository.save(train);
        }
        else{
            throw new TrainNumberNotExistException("Train Number Not Exist!");
        }
    }

    @Override
    public String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistException {
        Train tr = getTrainByTrainNumber(trainNumber);
        if(tr!=null){
            log.info("Deleting train docs with trainNumber: "+trainNumber);
            trainRepository.deleteById(trainNumber);
            return "Train Deleted Successfully";
        }
        else{
            throw new TrainNumberNotExistException("Train Number Not Exist!");
        }
    }
}

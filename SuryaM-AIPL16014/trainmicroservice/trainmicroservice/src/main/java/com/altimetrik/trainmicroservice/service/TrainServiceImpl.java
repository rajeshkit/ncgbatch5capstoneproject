package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainNumberAlreadyExistsException;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService{

    @Value("${train.microservice.base-url}")
    private String trainMicroserviceBaseUrl;

    public void printBaseUrl() {
        System.out.println("Train Microservice Base URL: " + trainMicroserviceBaseUrl);
    }

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrain(Train train) throws TrainNumberAlreadyExistsException {

        if (trainRepository.existsById(train.getTrainNumber())) {
            throw new TrainNumberAlreadyExistsException("Train with ID " + train.getTrainNumber() + " already exists.");
        }
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByNumber(int trainNumber) throws TrainNumberNotExistsException {

        return trainRepository.findById(trainNumber)
                .orElseThrow(() -> new TrainNumberNotExistsException("Train with number " + trainNumber + " not found"));
    }

    @Override
    public Train updateTrain(Train train) throws TrainNumberNotExistsException {

        if(getTrainByNumber(train.getTrainNumber())!=null) {
            return trainRepository.save(train);
        }
        return null;
    }

    @Override
    public String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistsException {

        return trainRepository.findById(trainNumber)
                .map(existingSchedule -> {
                    trainRepository.deleteById(trainNumber);
                    return "Train deleted successfully";
                })
                .orElseThrow(() -> new TrainNumberNotExistsException("Train with number " + trainNumber + " not found"));
    }

}

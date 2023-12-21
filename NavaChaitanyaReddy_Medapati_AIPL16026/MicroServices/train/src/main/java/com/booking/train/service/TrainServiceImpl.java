package com.booking.train.service;

import com.booking.train.exception.TrainNumberNotExistsException;
import com.booking.train.model.TrainResources;
import com.booking.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService{
    private TrainRepository trainRepository;
   @Autowired
    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public TrainResources addTrainResources(TrainResources trainResources) {
        return trainRepository.save(trainResources);
    }

    @Override
    public List<TrainResources> getAllTrainResources() {
        return trainRepository.findAll();
    }

    @Override
    public TrainResources getAllTrainResourcesById(Long trainNumber) throws TrainNumberNotExistsException {
        Optional<TrainResources> pro=trainRepository.findById(trainNumber);
        if(pro.isEmpty())
        {
            throw new TrainNumberNotExistsException("Train Number is not exists in the db table!!! check the Train Number");
        }
        return pro.get();
    }

    @Override
    public TrainResources updateTrainResource(TrainResources trainResources) throws TrainNumberNotExistsException {
        if(getAllTrainResourcesById(trainResources.getTrainNumber())!=null)
        {

            return trainRepository.save(trainResources);
        }
        else
        {
            return null;
        }
    }

    @Override
    public String deleteTrainResourceByTrainNumber(Long trainNumber) throws TrainNumberNotExistsException {
        String message="Trains Not Available by this Train Number";
        TrainResources trainResources=getAllTrainResourcesById(trainNumber);
        if(trainResources!=null)
        {
            trainRepository.deleteById(trainNumber);
            message="Train Resource deleted by this Train Number successfully";
            return message;
        }
        return  message;
    }
}

package com.TrainBookingSystem.Train.Services;


import com.TrainBookingSystem.Train.Exception.TrainIdNotFoundException;
import com.TrainBookingSystem.Train.Repository.TrainResourcesRepository;
import com.TrainBookingSystem.Train.model.TrainResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainResourcesRepository trainResourcesRepository;


    @Override
    public TrainResources addTrainResources(TrainResources trainResources) {
        return trainResourcesRepository.save(trainResources);
    }

    @Override
    public List<TrainResources> getAllTrainDetail() {

        return trainResourcesRepository.findAll();
    }

    @Override
    public TrainResources getTrainById(Long trainId) throws TrainIdNotFoundException {

        Optional<TrainResources> pro=trainResourcesRepository.findById(trainId);
        if(pro.isEmpty()) {
            throw new TrainIdNotFoundException("Train Id not found Exception!!!");
        }
        return pro.get();

    }

    @Override
    public TrainResources updateTrainDetail(TrainResources trainResources) throws TrainIdNotFoundException {
        if(getTrainById(trainResources.getTrainNumber())!=null) {
            return trainResourcesRepository.save(trainResources);
        }
        return null;
    }

    @Override
    public String deleteTrainById(Long trainId) throws TrainIdNotFoundException {
        String message="Train detail Does not exists to delete";
        TrainResources p=getTrainById(trainId);
        if(p!=null){
            trainResourcesRepository.deleteById(trainId);
            message="Train detail deleted successfully";
            return message;
        }
        return message;
    }


}

package com.booking_details.train.service;

import com.booking_details.train.exception.TrainIdNotFoundException;
import com.booking_details.train.model.TrainModel;
import com.booking_details.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TrainServiceImpl implements TrainService{
    private static final Logger LOGGER = Logger.getLogger("Train");

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public TrainModel addTrainDetails(TrainModel trainModel) {
        LOGGER.log(Level.INFO, "Adding all train details....");
        return trainRepository.save(trainModel);
    }

    @Override
    public List<TrainModel> getAllTrainDetails() {
        LOGGER.log(Level.INFO, "Fetching all train details....");

        return trainRepository.findAll();
    }

    @Override
    public TrainModel getAllTrainDetailsById(Long trainNumber) throws TrainIdNotFoundException {
        LOGGER.log(Level.INFO, "Fetching all train details by trainId....");
        Optional<TrainModel> details=trainRepository.findById(trainNumber);
        if(details.isEmpty()){
            throw new TrainIdNotFoundException();
        }
        return details.get();
    }

    @Override
    public TrainModel updateTrainDetails(TrainModel trainModel) throws TrainIdNotFoundException {
        LOGGER.log(Level.INFO, "Updating all train details....");
        if(getAllTrainDetailsById(trainModel.getTrainNumber())!=null)
        {
            return trainRepository.save(trainModel);
        }
        throw new TrainIdNotFoundException();
    }

    @Override
    public String deleteTrainDetailsById(Long trainNumber) throws TrainIdNotFoundException {

        TrainModel trainModel=getAllTrainDetailsById(trainNumber);
        if(trainModel!=null)
        {
            LOGGER.log(Level.INFO, "Deleting train details by trainId....");
            trainRepository.deleteById(trainNumber);
            return "Train details of the train number "+trainNumber + " has been successfully deleted!!!!" ;
        }
        throw new TrainIdNotFoundException();
    }

}

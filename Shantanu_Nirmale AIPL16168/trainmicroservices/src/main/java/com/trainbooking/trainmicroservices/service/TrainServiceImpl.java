package com.trainbooking.trainmicroservices.service;

import com.trainbooking.trainmicroservices.exception.TrainNumberNotExistException;
import com.trainbooking.trainmicroservices.model.Train;
import com.trainbooking.trainmicroservices.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService{


    @Autowired
    private TrainRepository trainRepository;

    Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);


    public Train addTrain(Train train){
        Train train1 = trainRepository.save(train);
        logger.info("Train Details Added to the Database");
        return train1;
    }

    public List<Train> getAllTrainDetails(){
        List<Train> allTrains = trainRepository.findAll();
        logger.info("All Train Details are{}", allTrains);
        return allTrains;
    }

    public Train getTrainByTrainNumber(int trainNumber) throws TrainNumberNotExistException {
        Optional<Train> train = trainRepository.findById(trainNumber);    //findById() return Optional type object

        if(train.isPresent()){
            logger.info("Train details fetched{}",train.get());
            return train.get();
        } else{
            throw new TrainNumberNotExistException("Train Number Not Exist!");
        }
    }

    public Train updateTrainDetails(Train train) throws TrainNumberNotExistException{
        if(getTrainByTrainNumber(train.getTrainNumber())!=null){
            Train train1 = trainRepository.save(train);
            logger.info("Train Details Updated Successfully");
            return train1;
        } else {
            throw new TrainNumberNotExistException("Train Number Not Exist !");
        }
    }

    public String deleteTrainByTrainNumber(int trainNumber)throws TrainNumberNotExistException{
        if(getTrainByTrainNumber(trainNumber)!=null){
            trainRepository.deleteById(trainNumber);
            logger.info("Train details deleted successfully !!!");
            return "Train Number " + trainNumber + " details deleted successfully !!!";
        } else {
            throw new TrainNumberNotExistException("Train Number Not Exist !");
        }
    }


}

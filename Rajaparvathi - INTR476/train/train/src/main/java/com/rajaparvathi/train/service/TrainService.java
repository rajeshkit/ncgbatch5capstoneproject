package com.rajaparvathi.train.service;

import com.rajaparvathi.train.model.Train;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TrainService {
    Train addTrainDetails(Train train);
    List<Train> getAllTrainDetails();
    Train searchTrainByNumber(int trainNumber);
    Train updateDetails(Train train);
    String removeTrainByNumber(int trainNumber);

}

package com.Train.train.repository;

import com.Train.train.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {


    Train findTrainByTrainNumber(int trainNumber);

    void deleteTrainByTrainNumber(int trainNumber);
}

package com.train.trainmicroservice.repository;

import com.train.trainmicroservice.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train,Long> {

    Train findByTrainNumber(int trainNumber);
}

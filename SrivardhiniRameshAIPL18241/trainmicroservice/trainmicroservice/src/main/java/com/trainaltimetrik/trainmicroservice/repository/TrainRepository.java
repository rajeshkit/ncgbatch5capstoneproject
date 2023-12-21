package com.trainaltimetrik.trainmicroservice.repository;

import com.trainaltimetrik.trainmicroservice.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {

}

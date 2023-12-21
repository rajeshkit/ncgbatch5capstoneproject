package com.altimetrik.trainmicroservices.repository;

import com.altimetrik.trainmicroservices.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TrainRepository extends JpaRepository<Train, Long> {

}

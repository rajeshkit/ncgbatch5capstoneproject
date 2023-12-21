package com.altimetrik.trainmicroservice.repository;

import com.altimetrik.trainmicroservice.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer>
{

}

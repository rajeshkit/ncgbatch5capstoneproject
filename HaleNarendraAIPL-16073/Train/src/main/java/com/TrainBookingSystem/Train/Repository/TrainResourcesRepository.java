package com.TrainBookingSystem.Train.Repository;

import com.TrainBookingSystem.Train.model.TrainResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainResourcesRepository extends JpaRepository<TrainResources,Long> {
}

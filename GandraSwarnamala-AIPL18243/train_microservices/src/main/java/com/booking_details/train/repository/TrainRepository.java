package com.booking_details.train.repository;

import com.booking_details.train.model.TrainModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<TrainModel, Long> {

}

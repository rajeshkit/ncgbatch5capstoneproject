package com.booking.train.repository;

import com.booking.train.model.TrainResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<TrainResources, Long> {

}

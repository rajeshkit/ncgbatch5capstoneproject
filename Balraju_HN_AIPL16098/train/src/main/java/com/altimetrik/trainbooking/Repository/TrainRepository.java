package com.altimetrik.trainbooking.Repository;

import com.altimetrik.trainbooking.modle.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {



}

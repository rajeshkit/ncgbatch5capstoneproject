package com.railwaybooking.Train.repository;

import com.railwaybooking.Train.model.TrainInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<TrainInfo,Long> {

}

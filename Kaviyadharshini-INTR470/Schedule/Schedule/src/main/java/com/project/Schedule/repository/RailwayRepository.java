package com.project.Schedule.repository;

import com.project.Schedule.model.TrainResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RailwayRepository extends JpaRepository<TrainResponse,Integer> {


}


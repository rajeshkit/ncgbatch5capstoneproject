package com.finalproject.schedule.repository;


import com.finalproject.schedule.model.TrainResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RailwaysRepository extends JpaRepository<TrainResponse,Integer> {


}

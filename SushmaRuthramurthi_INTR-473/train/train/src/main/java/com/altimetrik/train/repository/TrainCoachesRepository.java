package com.altimetrik.train.repository;

import com.altimetrik.train.entity.TrainCoaches;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainCoachesRepository extends JpaRepository<TrainCoaches,Integer> {
    public List<TrainCoaches> findBytrainnumber(int trainnumber);

}

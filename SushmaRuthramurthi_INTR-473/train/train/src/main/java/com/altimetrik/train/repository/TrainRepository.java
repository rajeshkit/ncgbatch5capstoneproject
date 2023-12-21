package com.altimetrik.train.repository;

import com.altimetrik.train.entity.Train;
import com.altimetrik.train.entity.TrainCoaches;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train,Integer> {
    public Train findBytrainNumber(int trainNumber);
}

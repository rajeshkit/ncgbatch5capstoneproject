package com.rajaparvathi.train.repository;

import com.rajaparvathi.train.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {
}

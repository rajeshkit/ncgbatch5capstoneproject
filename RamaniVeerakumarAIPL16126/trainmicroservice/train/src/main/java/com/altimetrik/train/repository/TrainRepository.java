package com.altimetrik.train.repository;

import com.altimetrik.train.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TrainRepository extends JpaRepository<Train,Integer> {
}

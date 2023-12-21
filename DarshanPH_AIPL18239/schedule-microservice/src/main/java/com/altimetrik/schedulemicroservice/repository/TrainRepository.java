package com.altimetrik.schedulemicroservice.repository;

import com.altimetrik.schedulemicroservice.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Integer> {
}

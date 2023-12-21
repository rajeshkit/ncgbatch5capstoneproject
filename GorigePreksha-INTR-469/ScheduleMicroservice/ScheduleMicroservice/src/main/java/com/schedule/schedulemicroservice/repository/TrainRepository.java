package com.schedule.schedulemicroservice.repository;

import com.schedule.schedulemicroservice.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train,Long> {


}

package com.altimetrik.trainbookingsystem.respository;

import com.altimetrik.trainbookingsystem.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,String> {


}

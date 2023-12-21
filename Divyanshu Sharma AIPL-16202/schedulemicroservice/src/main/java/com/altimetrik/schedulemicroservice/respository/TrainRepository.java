package com.altimetrik.schedulemicroservice.respository;
import com.altimetrik.schedulemicroservice.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TrainRepository extends JpaRepository<Train, Integer> {

}

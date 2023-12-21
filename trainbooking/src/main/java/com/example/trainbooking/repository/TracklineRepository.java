package com.example.trainbooking.repository;

import com.example.trainbooking.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TracklineRepository extends JpaRepository<Train,Integer>
{
    Optional<Train> findByName(String name);
}

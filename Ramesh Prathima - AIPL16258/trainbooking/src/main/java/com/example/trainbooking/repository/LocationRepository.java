package com.example.trainbooking.repository;

import com.example.trainbooking.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    }


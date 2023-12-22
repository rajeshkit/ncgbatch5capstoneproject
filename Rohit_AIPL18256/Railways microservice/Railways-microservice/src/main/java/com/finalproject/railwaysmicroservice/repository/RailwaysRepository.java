package com.finalproject.railwaysmicroservice.repository;

import com.finalproject.railwaysmicroservice.model.Railway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RailwaysRepository extends JpaRepository<Railway,Integer> {


}

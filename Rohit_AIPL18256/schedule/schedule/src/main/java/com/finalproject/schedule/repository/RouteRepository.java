package com.finalproject.schedule.repository;


import com.finalproject.schedule.model.RouteResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<RouteResponse,Integer> {


}

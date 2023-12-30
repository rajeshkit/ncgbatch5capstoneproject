package com.project.Schedule.repository;

import com.project.Schedule.model.RouteResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<RouteResponse,Integer> {


}

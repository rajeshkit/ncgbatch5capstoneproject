package com.trainbooking.schedulemicroservices.repository;

import com.trainbooking.schedulemicroservices.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}

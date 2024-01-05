package com.trainbooking.routemicroservices.repository;

import com.trainbooking.routemicroservices.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository  extends JpaRepository<Route, Integer> {
}

package com.routealtimetrik.routemicroservice.repository;

import com.routealtimetrik.routemicroservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,Integer> {
}

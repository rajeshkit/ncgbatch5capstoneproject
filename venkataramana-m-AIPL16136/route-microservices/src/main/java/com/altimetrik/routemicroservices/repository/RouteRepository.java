package com.altimetrik.routemicroservices.repository;

import com.altimetrik.routemicroservices.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}

package com.altimetrik.route.repository;

import com.altimetrik.route.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}

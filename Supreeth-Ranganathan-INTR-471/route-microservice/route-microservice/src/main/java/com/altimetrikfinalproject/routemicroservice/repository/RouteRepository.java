package com.altimetrikfinalproject.routemicroservice.repository;

import com.altimetrikfinalproject.routemicroservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}

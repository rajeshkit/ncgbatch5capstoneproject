package com.altimetrik.schedulemicroservice.repository;

import com.altimetrik.schedulemicroservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,Integer> {
}

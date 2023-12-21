package com.altimetrik.routemicroservice.repsitory;

import com.altimetrik.routemicroservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,String> {
}

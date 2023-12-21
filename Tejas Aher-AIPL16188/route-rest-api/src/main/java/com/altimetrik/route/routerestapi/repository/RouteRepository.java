package com.altimetrik.route.routerestapi.repository;

import com.altimetrik.route.routerestapi.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,String> {
}

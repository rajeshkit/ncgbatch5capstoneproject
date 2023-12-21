package com.Route.RouteMicroservice.repository;

import com.Route.RouteMicroservice.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {

    Route findByRouteId(int routeId);
}

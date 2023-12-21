package com.RouteMicroservices.Route.Repository;

import com.RouteMicroservices.Route.model.RouteResources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteResourcesRepository extends JpaRepository<RouteResources,Long> {
}

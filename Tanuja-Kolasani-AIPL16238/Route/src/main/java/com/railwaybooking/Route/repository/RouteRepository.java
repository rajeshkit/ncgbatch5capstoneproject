package com.railwaybooking.Route.repository;

import com.railwaybooking.Route.model.RouteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<RouteInfo,Long> {
}

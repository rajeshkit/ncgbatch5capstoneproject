package com.Booking.route.repository;

import com.Booking.route.model.RouteResources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<RouteResources,Long> {
}

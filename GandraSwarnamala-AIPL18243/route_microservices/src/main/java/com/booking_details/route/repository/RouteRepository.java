package com.booking_details.route.repository;

import com.booking_details.route.model.RouteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<RouteModel,Long> {
}

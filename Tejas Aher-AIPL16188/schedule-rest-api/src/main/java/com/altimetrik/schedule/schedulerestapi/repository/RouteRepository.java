package com.altimetrik.schedule.schedulerestapi.repository;

import com.altimetrik.schedule.schedulerestapi.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, String> {
}

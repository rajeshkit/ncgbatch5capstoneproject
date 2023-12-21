package com.altimetrik.SCHEDULE.repository;

import com.altimetrik.SCHEDULE.model.Route;
import com.altimetrik.SCHEDULE.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}

package com.altimetrik.trainroute.repository;

import com.altimetrik.trainroute.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,Integer> {

}
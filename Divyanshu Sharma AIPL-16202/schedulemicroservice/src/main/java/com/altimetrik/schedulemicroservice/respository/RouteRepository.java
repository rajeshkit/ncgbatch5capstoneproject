package com.altimetrik.schedulemicroservice.respository;
import com.altimetrik.schedulemicroservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}

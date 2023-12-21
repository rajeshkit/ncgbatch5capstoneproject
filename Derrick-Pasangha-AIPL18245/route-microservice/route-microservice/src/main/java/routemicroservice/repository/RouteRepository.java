package routemicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import routemicroservice.model.Route;

public interface RouteRepository extends JpaRepository<Route,Integer> {
}

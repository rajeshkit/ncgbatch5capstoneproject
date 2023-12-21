package schedulermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulermicroservice.model.Route;

public interface RouteRepository extends JpaRepository<Route,Integer> {
}

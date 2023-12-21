package Service;

import Model.Route;
import Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public Route updateRoute(Long id, Route updatedRoute) {
        Optional<Route> existingRouteOptional = routeRepository.findById(id);
        if (existingRouteOptional.isPresent()) {
            updatedRoute.setId(id);
            return routeRepository.save(updatedRoute);
        }
        return null;
    }

    public boolean deleteRouteById(Long id) {
        Optional<Route> routeOptional = routeRepository.findById(id);
        if (routeOptional.isPresent()) {
            routeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Route addRoute(Route route) {
        return route;
    }

    public void deleteRoute(Long id) {
    }

    public Object getRouteDetailsById(long l) {
        return null;
    }
}

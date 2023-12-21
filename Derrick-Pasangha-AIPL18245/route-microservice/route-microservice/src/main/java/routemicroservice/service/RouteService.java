package routemicroservice.service;

import routemicroservice.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    Route addRoute(Route route);

    List<Route> getAllRoutes();

    Optional<Route> getRouteById(int routeId);

    Route updateRoute(Route route);

    String deleteRouteById(int routeId);
}

package com.altimetrik.trainroute.service;

import com.altimetrik.trainroute.exception.RouteIdNotExistsException;
import com.altimetrik.trainroute.model.Route;
import com.altimetrik.trainroute.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        logger.info("Adding a new route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoute() {
        logger.info("Fetching all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(int routeId) throws RouteIdNotExistsException {
        logger.info("Fetching route by ID: {}", routeId);
        Optional<Route> optionalRoute = routeRepository.findById(routeId);

        if (optionalRoute.isEmpty()) {
            logger.warn("Route with ID {} not found", routeId);
            throw new RouteIdNotExistsException("Route does not exist in the database. Check the route ID.");
        }

        return optionalRoute.get();
    }

    @Override
    public Route updateRoute(Route route) throws RouteIdNotExistsException {
        int routeId = route.getRouteId();
        logger.info("Updating route with ID: {}", routeId);

        if (getRouteById(routeId) != null) {
            logger.info("Route found. Updating information.");
            return routeRepository.save(route);
        }

        logger.warn("Route with ID {} not found. Update failed.", routeId);
        return null;
    }

    @Override
    public String deleteRouteById(int routeId) throws RouteIdNotExistsException {
        logger.info("Deleting route with ID: {}", routeId);
        String message = "Route does not exist to delete";
        Route route = getRouteById(routeId);

        if (route != null) {
            routeRepository.deleteById(routeId);
            message = "Route deleted successfully";
            logger.info("Route with ID {} deleted successfully", routeId);
            return message;
        }

        logger.warn("Route with ID {} not found. Deletion failed.", routeId);
        return message;
    }
}

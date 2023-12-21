package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteIdAlreadyExists;
import com.altimetrik.routemicroservice.exception.RouteNotFoundException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) throws RouteIdAlreadyExists {
        if (routeRepository.existsById(route.getRouteId())) {
            throw new RouteIdAlreadyExists("Train with ID " + route.getRouteId() + " already exists.");
        }
        LOGGER.info("Adding route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        LOGGER.info("Getting all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(int routeId) throws RouteNotFoundException {
        LOGGER.info("Getting route by ID: {}", routeId);
        Optional<Route> routeOptional = routeRepository.findById(routeId);
        if (routeOptional.isEmpty()) {
            throw new RouteNotFoundException("Route with ID " + routeId + " not found in the database.");
        }
        return routeOptional.get();
    }

    @Override
    public Route updateRoute(Route route) throws RouteNotFoundException {
        LOGGER.info("Updating route: {}", route);
        if (getRouteById(route.getRouteId()) != null) {
            return routeRepository.save(route);
        }
        return null;
    }

    @Override
    public String deleteRouteById(int routeId) throws RouteNotFoundException {
        LOGGER.info("Deleting route by ID: {}", routeId);
        String message = "Route with ID " + routeId + " does not exist to delete";
        Route route = getRouteById(routeId);
        if (route != null) {
            routeRepository.deleteById(routeId);
            message = "Route deleted successfully";
        }
        return message;
    }
}
